patientBillingApp.controller('ReportsController', ['$rootScope', '$scope', 'LoggerService', 'PatientService',
    function ($rootScope, $scope, LoggerService, PatientService) {
        $scope.rc = {};

        $scope.rc.showReport = 'false';

        $scope.rc.selectedService = '';

        $scope.rc.selectedProduct = '';

        var pastDate = new Date();

        pastDate.setMonth(pastDate.getMonth() - 3);

        $scope.rc.startDate = kendo.toString(kendo.parseDate(pastDate, 'MM-dd-yyyy'), 'MM/dd/yyyy');

        $scope.rc.endDate = kendo.toString(kendo.parseDate(new Date(), 'MM-dd-yyyy'), 'MM/dd/yyyy');;

        PatientService.getAllServices().success(function (data) {
            $scope.rc.servicesMasterData = new kendo.data.DataSource({
                data: data
            });
        });

        PatientService.getAllProducts().success(function (data) {
            $scope.rc.productsMasterData = new kendo.data.DataSource({
                data: data
            });
        });

        $scope.rc.servicesgridoptions = {
            columns: [
                { field: 'chargeDate', title: 'Date', width: '50px', template: "#= kendo.toString(kendo.parseDate(chargeDate, 'MM-dd-yyyy'), 'MM-dd-yyyy') #" },
                { field: 'chargeName', title: 'Service', width: '100px' },
                { title: 'Patient', width: '50px', template: "#: firstName# #: lastName#" },
                { field: 'baseCharge', title: 'Base Charge', width: '50px', template: '#= kendo.toString(kendo.parseFloat(baseCharge), "c") #' },
                { field: 'tax', title: 'Tax', width: '50px', template: '#= kendo.toString(kendo.parseFloat(tax), "c") #' },
                { field: 'discount', title: 'Discount', width: '50px' },
                { field: 'totalCharge', title: 'Total', width: '50px', template: '#= kendo.toString(kendo.parseFloat(totalCharge), "c") #' },
                { field: 'comment', title: 'Comment', width: '50px' }
            ],
            selectable: 'row',
            navigatable: true,
            sortable: true
        }

        $scope.rc.productsgridoptions = {
            columns: [
                { field: 'date', title: 'Date', width: '50px', template: "#= kendo.toString(kendo.parseDate(date, 'MM-dd-yyyy'), 'MM-dd-yyyy') #" },
                { field: 'productName', title: 'Product', width: '100px' },
                { title: 'Patient', width: '50px', template: "#: firstName# #: lastName#" },
                { field: 'productBaseCharge', title: 'Base Charge', width: '50px', template: '#= kendo.toString(kendo.parseFloat(productBaseCharge), "c") #' },
                { field: 'productTax', title: 'Tax', width: '50px', template: '#= kendo.toString(kendo.parseFloat(productTax), "c") #' },
                { field: 'discount', title: 'Discount', width: '50px' },
                { field: 'productTotalCharge', title: 'Total', width: '50px', template: '#= kendo.toString(kendo.parseFloat(productTotalCharge), "c") #' },
                { field: 'comment', title: 'Comment', width: '50px' }
            ],
            selectable: 'row',
            navigatable: true,
            sortable: true
        }

        $scope.rc.paymentsgridoptions = {
            columns: [
                { field: 'creditDate', title: 'Date', width: '50px', template: "#= kendo.toString(kendo.parseDate(creditDate, 'MM-dd-yyyy'), 'MM-dd-yyyy') #" },
                { field: 'creditApplicationName', title: 'Payment For', width: '100px' },
                { title: 'Patient', width: '50px', template: "#: firstName# #: lastName#" },
                { field: 'creditName', title: 'Payment Type', width: '150px' },
                { field: 'creditAmount', title: 'Payment', width: '50px', template: '#= kendo.toString(kendo.parseFloat(creditAmount), "c") #' },
                { field: 'comment', title: 'Comment', width: '50px' }
            ],
            selectable: 'row',
            navigatable: true,
            sortable: true
        }

        $scope.rc.generateReport = function () {
            var serviceReportCriteria = {};

            serviceReportCriteria.startDate = kendo.toString(kendo.parseDate($scope.rc.startDate, 'MM/dd/yyyy'), 'MM-dd-yyyy hh:mm:ss');

            serviceReportCriteria.endDate = kendo.toString(kendo.parseDate($scope.rc.endDate, 'MM/dd/yyyy'), 'MM-dd-yyyy hh:mm:ss');

            $scope.promise = PatientService.getAccountReports(serviceReportCriteria);

            $scope.promise.success(function (data) {
                $scope.rc.showReport = 'true';

                if ($scope.rc.selectedService == '') {
                    $scope.rc.services = new kendo.data.ObservableArray(data.patientChargeDetails);
                }
                else {
                    var fileteredData = [];

                    data.patientChargeDetails.forEach(function (patientCharge) {
                        if (patientCharge.chargeName == $scope.rc.selectedService) {
                            fileteredData.push(patientCharge);
                        }
                    });

                    $scope.rc.services = new kendo.data.ObservableArray(fileteredData);
                }

                if ($scope.rc.selectedProduct == '') {
                    $scope.rc.products = new kendo.data.ObservableArray(data.patientProductDetails);
                }
                else {
                    var fileteredData = [];

                    data.patientProductDetails.forEach(function (product) {
                        if (product.productName == $scope.rc.selectedProduct) {
                            fileteredData.push(product);
                        }
                    });

                    $scope.rc.products = new kendo.data.ObservableArray(fileteredData);
                }

                $scope.rc.payments = new kendo.data.ObservableArray(data.patientPaymentDetails);
            });
        }

        $scope.rc.printReport = function () {
            var report = angular.element(document.getElementById('reports'))[0].innerHTML;

            var popupWin = window.open('', '_blank', 'width: 100%,height: 100%');

            popupWin.document.open("text/html");

            popupWin.document.write('<html><head><link rel="stylesheet" href="styles/main.css" /><link rel="stylesheet" href="styles/kendo/kendo.common.min.css"><link rel="stylesheet" href="styles/kendo/kendo.default.min.css"></head><body>' + report + '</html>');

            popupWin.document.close();

            $timeout(function () { 
            	popupWin.print(); 
            	popupWin.onfocus = function () {
                    popupWin.close();
                }
            
            },300);
        }

        setTimeout(function () {
            var grid = $("#servicesGrid").data("kendoGrid");

            $(grid.table).on('keydown', function (e) {
                selectRowUsingNavigation(grid, e);
            });
        }, 1000);
    }]);