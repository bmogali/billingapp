patientBillingApp.controller('PaymentModalInstanceController', ['$scope', '$rootScope', '$modal', '$modalInstance', 'payments', 'patientName', 'patientId', 'PatientService',
    function ($scope, $rootScope, $modal, $modalInstance, payments, patientName, patientId, PatientService) {

        $scope.pymic = {};

        $scope.pymic.payments = new kendo.data.ObservableArray(JSON.parse(JSON.stringify(payments)));

        $scope.pymic.patientName = patientName;

        $scope.pymic.patientId = patientId;

        $scope.pymic.totalPayments = kendo.toString(kendo.parseFloat($rootScope.sum($scope.pymic.payments, 'creditAmount')), 'c');

        PatientService.getAllCreditTypes().success(function (data) {
            $scope.pymic.creditTypesMasterData = new kendo.data.DataSource({
                data: data
            });
        });

        PatientService.getAllCreditApplications().success(function (data) {
            $scope.pymic.creditApplicationsMasterData = new kendo.data.DataSource({
                data: data
            });
        });

        $scope.pymic.paymentgridoptions = {
            columns: [
                { field: "creditApplicationName", editor: serviceDropDownEditor, width: "100px", headerTemplate: '<button class="btn btn-primary" style="float: right" ng-click="pymic.addNewPayment($event)"><span class="glyphicon glyphicon-plus"></span></button> Payment For' },
                { field: "creditName", title: "Credit", width: "50px", editor: creditTypesDropDownEditor },
                { field: "creditDate", title: "Date", width: "50px", template: "#= kendo.toString(kendo.parseDate(creditDate, 'MM-dd-yyyy'), 'MM-dd-yyyy') #", editor: datepicker, format: "{0:MM-dd-yyyy}" },
                { field: "creditAmount", title: "Amount", width: "50px", template: '#= kendo.toString(kendo.parseFloat(creditAmount), "c") #' },
                { field: "comment", title: "Comment", width: "50px" },
                { title: " ", width: "20px", template: '<button class="btn btn-danger" ng-click="pymic.deletePayment(dataItem)"><span class="glyphicon glyphicon-trash"></span></button>', attributes: { 'class': 'text-center' } }
            ],
            selectable: 'row',
            navigatable: true,
            height: 250,
            editable: true,
            scrollable: true,
            sortable: true
        }

        $scope.pymic.ok = function () {
            $scope.pymic.payments.forEach(function (entry) {
                delete entry.defaultCharge;

                $rootScope.formatDateTime(entry, 'creditDate');

                entry.patientId = $scope.pymic.patientId;
            });

            $scope.promise = PatientService.savePatientPayments($scope.pymic.payments);

            $scope.promise.success(function (entry) {
                $modalInstance.close(entry);
            });
        };

        $scope.pymic.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.pymic.addNewPayment = function ($event) {
            $event.stopPropagation();

            $event.preventDefault();

            var newPayment = {
                patientPaymentId: 0,
                creditTypeId: 0,
                creditApplicationName: '',
                creditApplicationId: 0,
                creditName: '',
                creditDate: new Date(),
                creditAmount: 0,
                comment: ''
            };

            $scope.pymic.payments.unshift(newPayment);
        }

        $scope.pymic.deletePayment = function (dataItem) {
            selectRow($scope.pymic.paymentdatagrid, dataItem);

            var modalInstance;

            modalInstance = $modal.open({
                templateUrl: 'views/modal/confirmmodalwindow.html',
                controller: 'ConfirmModalInstanceController'
            }), modalInstance.result.then(function () {
                var patientPaymentIds = [];

                patientPaymentIds.push(dataItem.patientPaymentId);

                $scope.promise = PatientService.deletePatientPayments(patientPaymentIds);

                $scope.promise.success(function () {
                    var dataToBeRemoved = '';

                    $scope.pymic.payments.forEach(function (payment) {
                        if (payment.patientPaymentId == dataItem.patientPaymentId) {
                            dataToBeRemoved = payment;
                        }
                    });

                    $scope.pymic.payments.splice($scope.pymic.payments.indexOf(dataToBeRemoved), 1);
                });
            });
        }

        function creditTypesDropDownEditor(container, options) {
            $('<input required data-text-field="creditName" data-value-field="creditName" data-bind="value:creditName"/>')
                .appendTo(container)
                .kendoDropDownList({
                    autoBind: false,
                    dataSource: $scope.pymic.creditTypesMasterData,
                    select: function (e) {
                        var dataItem = this.dataItem(e.item.index());

                        var selectedRecord = $scope.pymic.paymentdatagrid.dataItem($scope.pymic.paymentdatagrid.select());

                        selectedRecord.set("creditTypeId", dataItem.creditTypeId);
                    }
                });
        }

        function serviceDropDownEditor(container, options) {
            $('<input required data-text-field="creditApplicationName" data-value-field="creditApplicationName" data-bind="value:creditApplicationName"/>')
                .appendTo(container)
                .kendoComboBox({
                    autoBind: false,
                    dataSource: $scope.pymic.creditApplicationsMasterData,
                    select: function (e) {
                        var dataItem = this.dataItem(e.item.index());

                        var selectedRecord = $scope.pymic.paymentdatagrid.dataItem($scope.pymic.paymentdatagrid.select());

                        selectedRecord.set("creditApplicationId", dataItem.creditApplicationId);
                    }
                });
        }

        function datepicker(container, options) {
            $('<input data-text-field="' + options.field + '" data-value-field="' + options.field + '" data-bind="value:' + options.field + '" data-format="' + options.format + '"/>')
            .appendTo(container)
            .kendoDatePicker({});
        }
    }]);