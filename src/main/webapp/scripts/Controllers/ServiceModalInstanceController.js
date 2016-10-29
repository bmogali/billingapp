patientBillingApp.controller('ServiceModalInstanceController', ['$scope', '$rootScope', '$modal', '$modalInstance', 'serviceBills', 'patientName', 'patientId', 'PatientService',
    function ($scope, $rootScope, $modal, $modalInstance, serviceBills, patientName, patientId, PatientService) {

        var serviceTax;

        $scope.smic = {};

        $scope.smic.serviceBills = new kendo.data.ObservableArray(JSON.parse(JSON.stringify(serviceBills)));

        $scope.smic.patientName = patientName;

        $scope.smic.patientId = patientId;

        $scope.smic.totalServicesBill = kendo.toString(kendo.parseFloat($rootScope.sum($scope.smic.serviceBills, 'totalCharge')), 'c');

        PatientService.getAllServices().success(function (data) {
            $scope.smic.servicesMasterData = new kendo.data.DataSource({
                data: data
            });

            data.forEach(function (masterData) {
                $scope.smic.serviceBills.forEach(function (service) {
                    if (service.chargeTypeId == masterData.chargeTypeId) {
                        service.set('defaultCharge', masterData.defaultCharge);
                    }
                })
            });
        });

        PatientService.getTaxRates().success(function (data) {
            data.forEach(function (tax) {
                if (tax.taxItem == 'ServiceTax') {
                    serviceTax = tax.taxRate;
                }
            });
        });

        $scope.smic.servicegridoptions = {
            columns: [
                { field: "chargeName", width: "100px", editor: servicesDropDownEditor, headerTemplate: '<button class="btn btn-primary" style="float: right" ng-click="smic.addNewServiceBill($event)"><span class="glyphicon glyphicon-plus"></span></button> Service', attributes: { "class": "editable-cell" } },
                { field: "chargeDate", title: "Date", width: "50px", template: "#= kendo.toString(kendo.parseDate(chargeDate, 'MM-dd-yyyy'), 'MM-dd-yyyy') #", editor: datepicker, format: "{0:MM-dd-yyyy}", attributes: { "class": "editable-cell" } },
                { field: "numberOfSessions", title: "Sessions", width: "50px", attributes: { "class": "editable-cell" } },
                { field: "defaultCharge", title: "Default", width: "50px", template: '#= (defaultCharge == "") ? "" : kendo.toString(kendo.parseFloat(defaultCharge), "c") #', editable: false },
                { field: "baseCharge", title: "Charge", editor: editNumberWithoutSpinners, width: "50px", template: '#= (baseCharge == "") ? "" : kendo.toString(kendo.parseFloat(baseCharge), "c") #', attributes: { "class": "editable-cell" } },
                { field: "discount", title: "% Discount", editor: editNumberWithoutSpinners, width: "50px", attributes: { "class": "editable-cell" } },
                { field: "tax", title: "Tax", editor: editNumberWithoutSpinners, width: "50px", template: '#= (tax == "") ? "" : kendo.toString(kendo.parseFloat(tax), "c") #', attributes: { "class": "editable-cell" } },
                { field: "totalCharge", title: "Total", editor: editNumberWithoutSpinners, width: "50px", template: '#= (totalCharge == "") ? "" : kendo.toString(kendo.parseFloat(totalCharge), "c") #', attributes: { "class": "editable-cell" } },
                { field: "comment", title: "Comment", width: "150px", attributes: { "class": "editable-cell" } },
                { title: " ", width: "20px", template: '<button class="btn btn-danger" ng-click="smic.deleteServiceBill(dataItem)"><span class="glyphicon glyphicon-trash"></span></button>', attributes: { 'class': 'text-center' } }
            ],
            selectable: 'row',
            sortable: true,
            navigatable: true,
            height: 250,
            editable: true,
            scrollable: true,
            edit: function (e) {
                var field = e.container.find("input");
                setTimeout(function () {
                    field.select();
                }, 25);
            }
        }

        $scope.smic.ok = function () {
            $scope.smic.serviceBills.forEach(function (entry) {
                delete entry.defaultCharge;

                $rootScope.formatDateTime(entry, 'chargeDate');

                entry.patientId = $scope.smic.patientId;
            });

            $scope.promise = PatientService.savePatientCharges($scope.smic.serviceBills);

            $scope.promise.success(function (entry) {
                if (entry.length > 0) {
                    entry.forEach(function (detail) {
                        detail.defaultCharge = 0;
                    });
                }

                $modalInstance.close(entry);
            });
        };

        $scope.smic.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.smic.addNewServiceBill = function ($event) {
            $event.stopPropagation();

            $event.preventDefault();

            var newServiceBill = {
                patientChargeId: 0,
                chargeName: '',
                chargeTypeId: 0,
                chargeDate: new Date(),
                numberOfSessions: 1,
                baseCharge: '',
                tax: '',
                totalCharge: '',
                defaultCharge: '',
                comment: '',
                discount: ''
            };

            $scope.smic.serviceBills.unshift(newServiceBill);
        }

        $scope.smic.deleteServiceBill = function (dataItem) {
            selectRow($scope.smic.servicedatagrid, dataItem);

            var modalInstance;

            modalInstance = $modal.open({
                templateUrl: 'views/modal/confirmmodalwindow.html',
                controller: 'ConfirmModalInstanceController'
            }), modalInstance.result.then(function () {
                var patientChargeIds = [];

                patientChargeIds.push(dataItem.patientChargeId);

                $scope.promise = PatientService.deletePatientCharges(patientChargeIds);

                $scope.promise.success(function () {
                    var dataToBeRemoved = '';

                    $scope.smic.serviceBills.forEach(function (serviceBill) {
                        if (serviceBill.patientChargeId == dataItem.patientChargeId) {
                            dataToBeRemoved = serviceBill;
                        }
                    });

                    $scope.smic.serviceBills.splice($scope.smic.serviceBills.indexOf(dataToBeRemoved), 1);
                });
            });
        }

        function servicesDropDownEditor(container, options) {
            $('<input required data-text-field="chargeName" data-value-field="chargeName" data-bind="value:chargeName"/>')
                .appendTo(container)
                .kendoComboBox({
                    autoBind: false,
                    dataSource: $scope.smic.servicesMasterData,
                    select: function (e) {
                        var dataItem = this.dataItem(e.item.index());

                        var selectedRecord = $scope.smic.servicedatagrid.dataItem($scope.smic.servicedatagrid.select());

                        selectedRecord.set("defaultCharge", dataItem.defaultCharge);

                        selectedRecord.set("chargeTypeId", dataItem.chargeTypeId);
                    }
                });
        }

        function datepicker(container, options) {
            $('<input data-text-field="' + options.field + '" data-value-field="' + options.field + '" data-bind="value:' + options.field + '" data-format="' + options.format + '"/>')
            .appendTo(container)
            .kendoDatePicker({});
        }

        function editNumberWithoutSpinners(container, options) {
            $('<input data-text-field="' + options.field + '" ' +
                    'data-value-field="' + options.field + '" ' +
                    'data-bind="value:' + options.field + '" ' +
                    'data-format="' + options.format + '"/>')
                    .appendTo(container)
                    .kendoNumericTextBox({
                        spinners: false
                    });
        }

        $scope.smic.serviceBills.bind("change", function (e) {
            var baseCharge = kendo.parseFloat(e.items[0].get('baseCharge'));

            var discount = kendo.parseFloat(e.items[0].get('discount'));

            if (e.field == 'baseCharge') {
                e.items[0].set('tax', baseCharge * serviceTax / 100);

                var tax = kendo.parseFloat(e.items[0].get('tax'));

                e.items[0].set('totalCharge', baseCharge + tax);
            }

            if (e.field == 'discount') {
                var discountAmount = baseCharge * discount / 100;

                e.items[0].set('tax', kendo.parseFloat(baseCharge - discountAmount) * serviceTax / 100);

                var tax = kendo.parseFloat(e.items[0].get('tax'));

                e.items[0].set('totalCharge', kendo.parseFloat(baseCharge - discountAmount + tax));
            }
        });

        setTimeout(function () {
            var grid = $("#serviceGrid").data("kendoGrid");

            $(grid.table).on('keydown', function (e) {
                skipNonEditableCellsInGrid(e, grid);
            });
        }, 1000);
    }]);