patientBillingApp.controller('SetupController', ['$scope', '$modal', '$routeParams', 'LoggerService', 'PatientService',
    function ($scope, $modal, $routeParams, LoggerService, PatientService) {
        $scope.sc = {};

        var setupItemType = $routeParams.type;

        var changedServices = [];

        var changedProducts = [];

        var changedPaymentTypes = [];

        var globalCounter = -1;

        var taxData;

        var processTaxData = function (data) {
            taxData = data;

            data.forEach(function (tax) {
                if (tax.taxItem == 'ProductTax') {
                    $scope.sc.productTax = tax.taxRate;
                }

                if (tax.taxItem == 'ServiceTax') {
                    $scope.sc.serviceTax = tax.taxRate;
                }
            });
        }

        $scope.sc.getServicesMasterData = function () {
            PatientService.getAllServices().success(function (data) {
                $scope.sc.servicesMasterData = new kendo.data.ObservableArray(data);

                $scope.sc.servicesMasterData.bind('change', function (e) {
                    if (changedServices.indexOf(e.items[0].chargeTypeId) < 0)
                        changedServices.push(e.items[0].chargeTypeId);
                });
            });
        }

        $scope.sc.getProductsMasterData = function () {
            PatientService.getAllProducts().success(function (data) {
                $scope.sc.productsMasterData = new kendo.data.ObservableArray(data);

                $scope.sc.productsMasterData.bind('change', function (e) {
                    if (changedProducts.indexOf(e.items[0].productId) < 0)
                        changedProducts.push(e.items[0].productId);
                });
            });
        }

        $scope.sc.getPaymentTypesMasterData = function () {
            PatientService.getAllCreditTypes().success(function (data) {
                $scope.sc.paymentTypesMasterData = new kendo.data.ObservableArray(data);

                $scope.sc.paymentTypesMasterData.bind('change', function (e) {
                    if (changedPaymentTypes.indexOf(e.items[0].creditTypeId) < 0)
                        changedPaymentTypes.push(e.items[0].creditTypeId);
                });
            });
        }

        if (setupItemType == 'service')
            $scope.sc.getServicesMasterData();
        else if (setupItemType == 'product')
            $scope.sc.getProductsMasterData();
        else if (setupItemType == 'paymenttype')
            $scope.sc.getPaymentTypesMasterData();
        else if (setupItemType == 'tax')
            PatientService.getTaxRates().success(processTaxData).error(processErrors);

        $scope.sc.servicesgridoptions = {
            columns: [
                { field: "chargeName", width: "75px", headerTemplate: '<button class="btn btn-primary" style="float: right" ng-click="sc.addNewService($event);"><span class="glyphicon glyphicon-plus"></span></button> Service' },
                { field: "defaultCharge", title: "Default Charge", editor: editNumberWithoutSpinners, width: "50px", template: '#= kendo.toString(kendo.parseFloat(defaultCharge), "c") #' },
                { title: " ", width: "10px", template: '<button class="btn btn-danger" ng-click="sc.deleteService(dataItem)"><span class="glyphicon glyphicon-trash"></span></button>', attributes: { 'class': 'text-center' } }
            ],
            selectable: 'row',
            navigatable: true,
            height: 450,
            editable: true,
            scrollable: true,
            sortable: true
        }

        $scope.sc.productsgridoptions = {
            columns: [
                { field: "name", width: "75px", headerTemplate: '<button class="btn btn-primary" style="float: right" ng-click="sc.addNewProduct($event);"><span class="glyphicon glyphicon-plus"></span></button> Product' },
                { field: "defaultCharge", title: "Default Charge", editor: editNumberWithoutSpinners, width: "50px", template: '#= kendo.toString(kendo.parseFloat(defaultCharge), "c") #' },
                { title: " ", width: "10px", template: '<button class="btn btn-danger" ng-click="sc.deleteProduct(dataItem)"><span class="glyphicon glyphicon-trash"></span></button>', attributes: { 'class': 'text-center' } }
            ],
            selectable: 'row',
            navigatable: true,
            height: 450,
            editable: true,
            scrollable: true,
            sortable: true
        }

        $scope.sc.paymenttypesgridoptions = {
            columns: [
                { field: "creditName", width: "75px", headerTemplate: '<button class="btn btn-primary" style="float: right" ng-click="sc.addNewPaymentType($event);"><span class="glyphicon glyphicon-plus"></span></button> Payment Type' },
                { title: " ", width: "10px", template: '<button class="btn btn-danger" ng-click="sc.deletePaymentType(dataItem)"><span class="glyphicon glyphicon-trash"></span></button>', attributes: { 'class': 'text-center' } }
            ],
            selectable: 'row',
            navigatable: true,
            height: 450,
            editable: true,
            scrollable: true,
            sortable: true
        }

        $scope.sc.numericTextBoxOptions = {
            max: 100,
            min: 0,
            spinners: false
        }

        $scope.sc.addNewService = function ($event) {
            $event.stopPropagation();
            $event.preventDefault();

            globalCounter--;

            var newService = {
                chargeTypeId: globalCounter,
                chargeName: '',
                defaultCharge: 0,
                display: true
            };

            $scope.sc.servicesMasterData.unshift(newService);
        }

        $scope.sc.addNewProduct = function ($event) {
            $event.stopPropagation();
            $event.preventDefault();

            globalCounter--;

            var newProduct = {
                productId: globalCounter,
                name: '',
                defaultCharge: 0,
                display: true
            };

            $scope.sc.productsMasterData.unshift(newProduct);
        }

        $scope.sc.addNewPaymentType = function ($event) {
            $event.stopPropagation();
            $event.preventDefault();

            globalCounter--;

            var newPaymentType = {
                creditTypeId: globalCounter,
                creditName: 'New Entry',
                display: true
            };

            $scope.sc.paymentTypesMasterData.unshift(newPaymentType);
        }

        $scope.sc.updateServices = function (refreshGrid) {
            var updatedServices = [];

            $scope.sc.servicesMasterData.forEach(function (masterData) {
                changedServices.forEach(function (changedService) {
                    if (masterData.chargeTypeId == changedService)
                        updatedServices.push(masterData);
                });
            });

            updatedServices.forEach(function (service) {
                if (service.chargeTypeId < 0) {
                    service.chargeTypeId = 0;
                }
            });

            $scope.promise = PatientService.saveServices(updatedServices);

            if (refreshGrid) {
                $scope.promise.success(function (data) {
                    $scope.sc.getServicesMasterData();

                    changedServices = [];
                }).error(processErrors);
            }
        };

        $scope.sc.updateProducts = function (refreshGrid) {
            var updatedProducts = [];

            $scope.sc.productsMasterData.forEach(function (masterData) {
                changedProducts.forEach(function (changedProduct) {
                    if (masterData.productId == changedProduct)
                        updatedProducts.push(masterData);
                });
            });

            updatedProducts.forEach(function (product) {
                if (product.productId < 0) {
                    product.productId = 0;
                }
            });

            $scope.promise = PatientService.saveProducts(updatedProducts);

            if (refreshGrid) {
                $scope.promise.success(function (data) {
                    $scope.sc.getProductsMasterData();

                    changedProducts = [];
                }).error(processErrors);
            }
        };

        $scope.sc.updatePaymentTypes = function (refreshGrid) {
            var updatedPaymentTypes = [];

            $scope.sc.paymentTypesMasterData.forEach(function (masterData) {
                changedPaymentTypes.forEach(function (changedPaymentType) {
                    if (masterData.creditTypeId == changedPaymentType)
                        updatedPaymentTypes.push(masterData);
                });
            });

            updatedPaymentTypes.forEach(function (product) {
                if (product.creditTypeId < 0) {
                    product.creditTypeId = 0;
                }
            });

            $scope.promise = PatientService.saveCreditTypes(updatedPaymentTypes);

            if (refreshGrid) {
                $scope.promise.success(function (data) {
                    $scope.sc.getPaymentTypesMasterData();

                    changedPaymentTypes = [];
                }).error(processErrors);
            }
        };

        $scope.sc.deleteService = function (dataItem) {
            selectRow($scope.sc.servicesdatagrid, dataItem);

            var modalInstance;

            modalInstance = $modal.open({
                templateUrl: 'views/modal/confirmmodalwindow.html',
                controller: 'ConfirmModalInstanceController'
            }), modalInstance.result.then(function () {
                dataItem.set('display', false);

                $scope.sc.updateServices(false);

                setTimeout(function () {
                    var masterDataToBeRemoved = '';

                    $scope.sc.servicesMasterData.forEach(function (masterData) {
                        if (masterData.chargeTypeId == dataItem.chargeTypeId) {
                            masterDataToBeRemoved = masterData;
                        }
                    });

                    $scope.sc.servicesMasterData.splice($scope.sc.servicesMasterData.indexOf(masterDataToBeRemoved), 1);
                }, 100);
            });
        }

        $scope.sc.deleteProduct = function (dataItem) {
            selectRow($scope.sc.productsdatagrid, selectRow);

            var modalInstance;

            modalInstance = $modal.open({
                templateUrl: 'views/modal/confirmmodalwindow.html',
                controller: 'ConfirmModalInstanceController'
            }), modalInstance.result.then(function () {
                dataItem.set('display', false);

                $scope.sc.updateProducts(false);

                setTimeout(function () {
                    var masterDataToBeRemoved = '';

                    $scope.sc.productsMasterData.forEach(function (masterData) {
                        if (masterData.productId == dataItem.productId) {
                            masterDataToBeRemoved = masterData;
                        }
                    });

                    $scope.sc.productsMasterData.splice($scope.sc.productsMasterData.indexOf(masterDataToBeRemoved), 1);
                }, 100);
            });
        }

        $scope.sc.deletePaymentType = function (dataItem) {
            selectRow($scope.sc.paymenttypesdatagrid, selectRow);

            var modalInstance;

            modalInstance = $modal.open({
                templateUrl: 'views/modal/confirmmodalwindow.html',
                controller: 'ConfirmModalInstanceController'
            }), modalInstance.result.then(function () {
                dataItem.set('display', false);

                $scope.sc.updatePaymentTypes(false);

                setTimeout(function () {
                    var masterDataToBeRemoved = '';

                    $scope.sc.paymentTypesMasterData.forEach(function (masterData) {
                        if (masterData.creditTypeId == dataItem.creditTypeId) {
                            masterDataToBeRemoved = masterData;
                        }
                    });

                    $scope.sc.paymentTypesMasterData.splice($scope.sc.paymentTypesMasterData.indexOf(masterDataToBeRemoved), 1);
                }, 100);
            });
        }

        $scope.sc.updateTaxes = function () {
            taxData.forEach(function (tax) {
                if (tax.taxItem == 'ProductTax') {
                    tax.taxRate = $scope.sc.productTax;
                }

                if (tax.taxItem == 'ServiceTax') {
                    tax.taxRate = $scope.sc.serviceTax;
                }
            });

            $scope.promise = PatientService.saveTaxRates(taxData);

            $scope.promise.success(function (data) {
                LoggerService.logSuccess('Taxes saved successfully.');
            }).error(processErrors);
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

        var processErrors = function () {
            LoggerService.logError("Network error.");
        }
    }]);