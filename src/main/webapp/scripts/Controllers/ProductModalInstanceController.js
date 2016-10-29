patientBillingApp.controller('ProductModalInstanceController', ['$scope', '$rootScope', '$modal', '$modalInstance', 'productBills', 'patientName', 'patientId', 'PatientService',
    function ($scope, $rootScope, $modal, $modalInstance, productBills, patientName, patientId, PatientService) {

        var productTax;

        $scope.pmic = {};

        $scope.pmic.productBills = new kendo.data.ObservableArray(JSON.parse(JSON.stringify(productBills)));

        $scope.pmic.patientName = patientName;

        $scope.pmic.patientId = patientId;

        $scope.pmic.totalProductsBill = kendo.toString(kendo.parseFloat($rootScope.sum($scope.pmic.productBills, 'productTotalCharge')), 'c');

        PatientService.getAllProducts().success(function (data) {
            $scope.pmic.productsMasterData = new kendo.data.DataSource({
                data: data
            });

            data.forEach(function (masterData) {
                $scope.pmic.productBills.forEach(function (product) {
                    if (product.productId == masterData.productId) {
                        product.set("defaultCharge", masterData.defaultCharge);
                    }
                })
            });
        });

        PatientService.getTaxRates().success(function (data) {
            data.forEach(function (tax) {
                if (tax.taxItem == 'ProductTax') {
                    productTax = tax.taxRate;
                }
            });
        });

        $scope.pmic.productgridoptions = {
            columns: [
                { field: "productName", width: "100px", editor: productsDropDownEditor, headerTemplate: '<button class="btn btn-primary" style="float: right" ng-click="pmic.addNewProductBill($event)"><span class="glyphicon glyphicon-plus"></span></button> Product', attributes: { "class": "editable-cell" } },
                { field: "date", title: "Date", width: "50px", template: "#= kendo.toString(kendo.parseDate(date, 'MM-dd-yyyy'), 'MM-dd-yyyy') #", editor: datepicker, format: "{0:MM-dd-yyyy}", attributes: { "class": "editable-cell" } },
                { field: "defaultCharge", title: "Default", width: "50px", template: '#= (defaultCharge == "") ? "" : kendo.toString(kendo.parseFloat(defaultCharge), "c") #', editable: false },
                { field: "productBaseCharge", title: "Charge", editor: editNumberWithoutSpinners, width: "50px", template: '#= (productBaseCharge == "") ? "" : kendo.toString(kendo.parseFloat(productBaseCharge), "c") #', attributes: { "class": "editable-cell" } },
                { field: "discount", title: "% Discount", editor: editNumberWithoutSpinners, width: "50px", attributes: { "class": "editable-cell" } },
                { field: "quantity", title: "Quantity", width: "50px", attributes: { "class": "editable-cell" } },
                { field: "productTax", title: "Tax", editor: editNumberWithoutSpinners, width: "50px", template: '#= (productTax == "") ? "" : kendo.toString(kendo.parseFloat(productTax), "c") #', attributes: { "class": "editable-cell" } },
                { field: "productTotalCharge", title: "Total", editor: editNumberWithoutSpinners, width: "50px", template: '#= (productTotalCharge == "") ? "" : kendo.toString(kendo.parseFloat(productTotalCharge), "c") #', attributes: { "class": "editable-cell" } },
                { field: "comment", title: "Comment", width: "50px", attributes: { "class": "editable-cell" } },
                { title: " ", width: "20px", template: '<button class="btn btn-danger" ng-click="pmic.deleteProductBill(dataItem)"><span class="glyphicon glyphicon-trash"></span></button>', attributes: { 'class': 'text-center' } }
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

        $scope.pmic.ok = function () {
            $scope.pmic.productBills.forEach(function (entry) {
                delete entry.defaultCharge;

                $rootScope.formatDateTime(entry, 'date');

                entry.patientId = $scope.pmic.patientId;
            });

            $scope.promise = PatientService.savePatientProducts($scope.pmic.productBills);

            $scope.promise.success(function (entry) {
                if (entry.length > 0) {
                    entry.forEach(function (detail) {
                        detail.defaultCharge = 0;
                    });
                }

                $modalInstance.close(entry);
            });
        };

        $scope.pmic.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.pmic.addNewProductBill = function ($event) {
            $event.stopPropagation();

            $event.preventDefault();

            var newProductBill = {
                patientProductId: 0,
                productId: 0,
                productName: '',
                date: new Date(),
                defaultCharge: '',
                productBaseCharge: '',
                discount: '',
                quantity: 1,
                productTax: '',
                productTotalCharge: '',
                comment: ''
            };

            $scope.pmic.productBills.unshift(newProductBill);
        }

        $scope.pmic.deleteProductBill = function (dataItem) {
            selectRow($scope.pmic.productdatagrid, dataItem);

            var modalInstance;

            modalInstance = $modal.open({
                templateUrl: 'views/modal/confirmmodalwindow.html',
                controller: 'ConfirmModalInstanceController'
            }), modalInstance.result.then(function () {
                var patientProductIds = [];

                patientProductIds.push(dataItem.patientProductId);

                $scope.promise = PatientService.deletePatientProducts(patientProductIds);

                $scope.promise.success(function () {
                    var dataToBeRemoved = '';

                    $scope.pmic.productBills.forEach(function (payment) {
                        if (payment.patientProductId == dataItem.patientProductId) {
                            dataToBeRemoved = payment;
                        }
                    });

                    $scope.pmic.productBills.splice($scope.pmic.productBills.indexOf(dataToBeRemoved), 1);
                });
            });
        }

        function productsDropDownEditor(container, options) {            
            $('<input data-text-field="name" data-value-field="name" data-bind="value:productName"/>')
                .appendTo(container)
                .kendoComboBox({
                    autoBind: false,
                    dataSource: $scope.pmic.productsMasterData,
                    select: function (e) {
                        var dataItem = this.dataItem(e.item.index());

                        var selectedRecord = $scope.pmic.productdatagrid.dataItem($scope.pmic.productdatagrid.select());

                        selectedRecord.set("defaultCharge", dataItem.defaultCharge);

                        selectedRecord.set("productId", dataItem.productId);
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

        $scope.pmic.productBills.bind("change", function (e) {
            var productBaseCharge = kendo.parseFloat(e.items[0].get('productBaseCharge'));

            var discount = kendo.parseFloat(e.items[0].get('discount'));

            if (e.field == 'productBaseCharge') {
                e.items[0].set('productTax', productBaseCharge * productTax / 100);

                var tax = kendo.parseFloat(e.items[0].get('productTax'));

                e.items[0].set('productTotalCharge', productBaseCharge + tax);
            }

            if (e.field == 'discount') {
                var discount = productBaseCharge * discount / 100;

                e.items[0].set('productTax', kendo.parseFloat(productBaseCharge - discount) * productTax / 100);

                var tax = kendo.parseFloat(e.items[0].get('productTax'));

                e.items[0].set('productTotalCharge', kendo.parseFloat(productBaseCharge - discount + tax));
            }
        });

        setTimeout(function () {
            var grid = $("#productsGrid").data("kendoGrid");

            $(grid.table).on('keydown', function (e) {
                skipNonEditableCellsInGrid(e, grid);
            });
        }, 1000);
    }]);