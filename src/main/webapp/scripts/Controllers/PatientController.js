patientBillingApp.controller('PatientController', ['$scope', '$modal', '$rootScope', '$q', '$timeout', '$compile', '$http', 'PatientService', 'LoggerService', 'blockUI',
    function ($scope, $modal, $rootScope, $q, $timeout, $compile, $http, PatientService, LoggerService, blockUI, ApiBaseUrl) {
        var processEstimate = function (data) {
            data.forEach(function (entry) {
                entry.isSelected = false;
            })

            $scope.pc.patientDetails.patientEstimates = new kendo.data.ObservableArray(data);
        }

        var processImages = function (data) {
            console.log(data);
            $scope.pc.patientDetails.pictures = new kendo.data.ObservableArray(data);
        }

        var processErrors = function () {
            LoggerService.logError("Network error.");
        }

        var processPatient = function (data) {
            if (angular.isUndefined(data.patientProcedures))
                data.patientProcedures = [];

            // TODO : Service side fix should be available.
            data.patientProcedures.forEach(function (procedure) {
                procedure.ProcedureDate = procedure.procedureDate;
                procedure.TreatmentSequenceId = procedure.sequence;
            });

            if (angular.isUndefined(data.patientBillingDetails)) {
                data.patientBillingDetails = {};

                data.patientBillingDetails.patientChargeDetails = [];

                data.patientBillingDetails.patientProductDetails = [];

                data.patientBillingDetails.patientPaymentDetails = [];
            }

            $scope.pc.assignDefaultCharge(data.patientBillingDetails.patientChargeDetails);

            $scope.pc.assignDefaultCharge(data.patientBillingDetails.patientProductDetails);

            $scope.pc.assignDefaultCharge(data.patientBillingDetails.patientPaymentDetails);

            $scope.pc.patientDetails = new kendo.data.ObservableObject(data);

            $scope.pc.calculateMonetoryValue();

            $scope.pc.patientRowSelected = true;
        }

        var bindSelectedPatient = function (patientId) {
            $scope.promise = PatientService.getPatientDetails(patientId);

            $scope.promise
                .success(processPatient)
                .error(processErrors);

            $scope.pc.patientRowSelected = true;

            $scope.pc.currentTab = 'PatientTab';

            $scope.pc.procedureSelected = false;

            if (!$scope.$$phase) {
                $scope.$apply();
            }
        }

        $scope.pc = {};

        $scope.pc.toggleText = 'Hide Results';

        $scope.pc.currentTab = 'DefaultTab';

        $scope.pc.imageFile = '';

        $scope.pc.imageDescription = ''

        $scope.pc.selectedImages = 0;

        $scope.pc.patientFullName = function () {
            return $scope.pc.patientDetails.firstName + ' ' + $scope.pc.patientDetails.lastName;
        }

        $scope.pc.currentDate = function () {
            return kendo.toString(kendo.parseDate(new Date(), 'MM-dd-yyyy'), 'MM-dd-yyyy')
        }

        $scope.pc.billingPeriod = function () {
            return $scope.pc.startDate + ' - ' + $scope.pc.endDate;
        }

        $scope.pc.secondFullAddress = function () {
            return $scope.pc.patientDetails.mailingCity + ' ' + $scope.pc.patientDetails.mailingState + ' ' + $scope.pc.patientDetails.mailingZip;
        }

        $scope.pc.formatDate = function (date) {
            return kendo.toString(kendo.parseDate(date, 'MM-dd-yyyy'), 'MM-dd-yyyy');
        }

        $scope.pc.formatCurrency = function (monetoryValue) {
            return kendo.toString(kendo.parseFloat(monetoryValue), "c");
        }

        if (PatientService.selectedPatientId != 0) {
            bindSelectedPatient(PatientService.selectedPatientId);

            PatientService.selectedPatientId = 0;
        }

        $scope.pc.patientSearchResults = new kendo.data.DataSource({
            type: "json",
            transport: {
                read: function (options) {

                    $scope.pc.patientRowSelected = false;

                    if ($scope.pc.isCollapsed) {
                        $scope.pc.toggleResults();
                    }

                    $scope.pc.patientDetails = {};

                    $scope.promise = PatientService.searchPatients($scope.pc.searchCriteria, options);

                    $scope.promise.success(function (data) {
                        data.Patients = data;
                        data.TotalRecords = data.length;
                        options.success(data);
                        $scope.pc.currentTab = 'DefaultTab';
                        if (data.length != 0)
                            $scope.pc.datagrid.pager.page(1);
                    }).error(processErrors);
                }
            },
            pageSize: 20,
            serverPaging: false,
            serverFiltering: false,
            serverSorting: false,
            schema: {
                data: 'Patients',
                total: 'TotalRecords'
            }
        });

        $scope.pc.patientSearchResultsGridOptions = {
            dataSource: $scope.pc.patientSearchResults,
            autoBind: false,
            columns: [
                { field: "lastName", title: "Last Name", width: "100px" },
                { field: "firstName", title: "First Name", width: "100px" },
                { field: "mailingAddress1", title: "Mailing Address 1", width: "100px" },
                { field: "mailingCity", title: "Mailing City", width: "100px" }
            ],
            sortable: true,
            pageable: true,
            selectable: 'row',
            height: 250,
            navigatable: true,
            change: function (e) {

                var selectedItem = $scope.pc.datagrid.dataItem($scope.pc.datagrid.select());

                bindSelectedPatient(selectedItem.patientId);
            }
        };

        $scope.pc.mappinggridoptions = {
            columns: [
                { field: 'procedureName', title: 'Procedure', width: '170px', headerTemplate: '<button class="btn btn-primary" style="float: right" ng-click="pc.addProcedure($event)"><span class="glyphicon glyphicon-plus"></span></button> Procedure' },
                {
                    field: 'ProcedureDate', title: 'Date', width: '70px', template: "#= kendo.toString(kendo.parseDate(ProcedureDate, 'MM-dd-yyyy'), 'MM-dd-yyyy') #", sortable: {
                        compare: function (a, b) {
                            var date1 = kendo.parseDate(a.ProcedureDate, 'MM-dd-yyyy');
                            var date2 = kendo.parseDate(b.ProcedureDate, 'MM-dd-yyyy');
                            return date1 - date2;
                        }
                    }
                },
                { field: 'TreatmentSequenceId', title: '#', width: '30px' },
                { title: 'Actions', width: '50px', template: '<button class="btn btn-primary" ng-click="pc.printProcedure(dataItem, $event)"><span class="glyphicon glyphicon-print"></span></button> <button class="btn btn-danger" ng-click="pc.deleteProcedure(dataItem)"><span class="glyphicon glyphicon-trash"></span></button>', attributes: { 'class': 'text-center' } }
            ],
            selectable: 'row',
            sortable: {
                mode: 'multiple',
                allowUnsort: true
            },
            navigatable: true,
            height: 155,
            change: function (e) {
                var selectedItem = $scope.pc.mappingdatagrid.dataItem($scope.pc.mappingdatagrid.select());

                if (selectedItem.patientProcedureId > 0) {
                    PatientService.getProcedureDetails(selectedItem.patientProcedureId).success(function (data) {
                        $scope.pc.selectedProcedure = data;

                        $scope.pc.selectedProcedureName = selectedItem.procedureName;
                    }).error(processErrors);
                }
                else {
                    $scope.pc.selectedProcedure = selectedItem;
                }

                $scope.pc.currentTab = selectedItem.procedureName;

                $scope.pc.lastLoadedProcedure = $scope.pc.currentTab;

                $scope.pc.procedureSelected = true;

                if (!$scope.$$phase)
                    $scope.$apply();
            }
        }

        $scope.pc.servicegridoptions = {
            columns: [
                { field: "chargeName", title: "Service", width: "80px" },
                { field: "chargeDate", title: "Date", width: "50px", template: "#= kendo.toString(kendo.parseDate(chargeDate, 'MM-dd-yyyy'), 'MM-dd-yyyy') #" },
                { field: "numberOfSessions", title: "Sessions", width: "50px" },
                { field: "totalCharge", title: "Charge", width: "50px", template: '#= kendo.toString(kendo.parseFloat(totalCharge), "c") #' }
            ],
            selectable: 'row',
            sortable: true,
            navigatable: true,
            height: 125
        }

        $scope.pc.productgridoptions = {
            columns: [
                { field: "productName", title: "Product", width: "80px" },
                { field: "date", title: "Date", width: "50px", template: "#= kendo.toString(kendo.parseDate(date, 'MM-dd-yyyy'), 'MM-dd-yyyy') #" },
                { field: "quantity", title: "Quantity", width: "50px" },
                { field: "productTotalCharge", title: "Charge", width: "50px", template: '#= kendo.toString(kendo.parseFloat(productTotalCharge), "c") #' }
            ],
            selectable: 'row',
            sortable: true,
            navigatable: true,
            height: 130
        }

        $scope.pc.paymentgridoptions = {
            columns: [
                { field: "creditName", title: "Payment Type", width: "80px" },
                { field: "creditDate", title: "Date", width: "50px", template: "#= kendo.toString(kendo.parseDate(creditDate, 'MM-dd-yyyy'), 'MM-dd-yyyy') #" },
                { field: "comment", title: "Comment", width: "50px" },
                { field: "creditAmount", title: "Amount", width: "50px", template: '#= kendo.toString(kendo.parseFloat(creditAmount), "c") #' }
            ],
            selectable: 'row',
            sortable: true,
            navigatable: true,
            height: 130
        }

        $scope.pc.picturesgridoptions = {
            columns: [
                { field: 'Preview', template: '<img src="#= imageName #" alt="image" style="max-width:80px;max-height:80px;" />', width: "60px", attributes: { 'class': 'text-center' }, headerTemplate: '<button ng-show="pc.selectedImages >= 1" class="btn btn-primary" style="float: right" ng-click="pc.compareImages($event)"><span class="glyphicon glyphicon-search"></span></button> Preview' },
                { field: 'description', title: 'Description', width: '100px' },
                { field: 'fileName', title: 'FileName', width: '100px' },
                { field: 'date', title: 'Upload Date', width: '50px', template: "#= kendo.toString(kendo.parseDate(date, 'MM-dd-yyyy'), 'MM-dd-yyyy') #" },
                { title: ' ', width: '15px', template: '<button class="btn btn-danger" ng-click="pc.deleteImage(dataItem)"><span class="glyphicon glyphicon-trash"></span></button>', attributes: { 'class': 'text-center' } }
            ],
            selectable: 'multiple',
            navigatable: true,
            height: 250,
            sortable: true,
            change: function (e) {
                var rows = $scope.pc.picturesdatagrid.select();

                $scope.pc.selectedImages = rows.length;

                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            }
        }

        $scope.pc.toggleResults = function () {
            $scope.pc.isCollapsed = !$scope.pc.isCollapsed;

            if ($scope.pc.isCollapsed){
                $scope.pc.toggleText = 'Show Results';
                $scope.pc.mappinggridoptions.height = 355;
                $scope.pc.mappingDataGridOptionChanged = "X";
            }                
            else {
                $scope.pc.toggleText = 'Hide Results';
                $scope.pc.mappinggridoptions.height = 155;
                $scope.pc.mappingDataGridOptionChanged = "Y";
            }
        }

        $scope.pc.toggleWindow = function (selectedTab) {
            if (selectedTab == 'ProcedureTab') {
                $scope.pc.currentTab = $scope.pc.lastLoadedProcedure;
            }
            else {
                if (selectedTab == 'BillingTab') {
                    $scope.promise = PatientService.getPatientEstimates($scope.pc.patientDetails.patientId);

                    $scope.promise.success(processEstimate).error(processErrors);
                }
                else if (selectedTab == 'Pictures') {
                    $scope.promise = PatientService.getPatientImages($scope.pc.patientDetails.patientId);

                    $scope.promise.success(processImages).error(processErrors);
                }

                $scope.pc.currentTab = selectedTab;
            }
        }

        $scope.pc.openServiceBillingModal = function () {
            var modalInstance;

            modalInstance = $modal.open({
                templateUrl: "views/modal/servicemodalwindow.html",
                controller: 'ServiceModalInstanceController',
                windowClass: 'servicebilling-modal-window',
                resolve: {
                    serviceBills: function () {
                        return $scope.pc.patientDetails.patientBillingDetails.patientChargeDetails;
                    },
                    patientName: function () {
                        return $scope.pc.patientDetails.firstName + ' ' + $scope.pc.patientDetails.lastName;
                    },
                    patientId: function () {
                        return $scope.pc.patientDetails.patientId;
                    }
                }
            }), modalInstance.result.then(function (data) {
                $scope.pc.patientDetails.patientBillingDetails.patientChargeDetails = new kendo.data.ObservableArray(data);

                $scope.pc.calculateMonetoryValue();
            });
        }

        $scope.pc.openProductBillingModal = function () {
            var modalInstance;

            modalInstance = $modal.open({
                templateUrl: "views/modal/productmodalwindow.html",
                controller: 'ProductModalInstanceController',
                windowClass: 'productbilling-modal-window',
                resolve: {
                    productBills: function () {
                        return $scope.pc.patientDetails.patientBillingDetails.patientProductDetails;
                    },
                    patientName: function () {
                        return $scope.pc.patientDetails.firstName + ' ' + $scope.pc.patientDetails.lastName;
                    },
                    patientId: function () {
                        return $scope.pc.patientDetails.patientId;
                    }
                }
            }), modalInstance.result.then(function (data) {
                $scope.pc.patientDetails.patientBillingDetails.patientProductDetails = new kendo.data.ObservableArray(data);

                $scope.pc.calculateMonetoryValue();
            });
        }

        $scope.pc.openPaymentModal = function () {
            var modalInstance;

            modalInstance = $modal.open({
                templateUrl: "views/modal/paymentmodalwindow.html",
                controller: 'PaymentModalInstanceController',
                windowClass: 'payment-modal-window',
                resolve: {
                    payments: function () {
                        return $scope.pc.patientDetails.patientBillingDetails.patientPaymentDetails;
                    },
                    patientName: function () {
                        return $scope.pc.patientDetails.firstName + ' ' + $scope.pc.patientDetails.lastName;
                    },
                    patientId: function () {
                        return $scope.pc.patientDetails.patientId;
                    }
                }
            }), modalInstance.result.then(function (data) {
                data.forEach(function (entry) {
                    $rootScope.formatDateTime(entry, 'creditDate');

                    $scope.pc.assignPatientId(entry);
                });

                $scope.pc.patientDetails.patientBillingDetails.patientPaymentDetails = data;

                $scope.pc.calculateMonetoryValue();
            });
        }

        $scope.pc.openEstimateModal = function () {
            var modalInstance;

            modalInstance = $modal.open({
                templateUrl: "views/modal/estimatemodalwindow.html",
                controller: 'EstimateModalInstanceController',
                windowClass: 'estimate-modal-window',
                resolve: {
                    estimates: function () {
                        return $scope.pc.patientDetails.patientEstimates;
                    },
                    patientName: function () {
                        return $scope.pc.patientDetails.firstName + ' ' + $scope.pc.patientDetails.lastName;
                    }
                }
            }), modalInstance.result.then(function (data) {
                data.estimates.forEach(function (estimate) {
                    delete estimate.isSelected;

                    $rootScope.formatDateTime(estimate, 'dateOfService');

                    $scope.pc.assignPatientId(estimate);
                });

                if (data.action == 'SaveEstimates') {
                    $scope.promise = PatientService.savePatientEstimates(data.estimates);

                    $scope.promise.success(processEstimate).error(processErrors);

                    LoggerService.logSuccess('Estimates saved successfully.');
                }
                else if (data.action == 'ActivateEstimates') {
                    data.estimates.forEach(function (estimate) {
                        var newServiceBill = {
                            patientChargeId: 0,
                            patientId: $scope.pc.patientDetails.patientId,
                            chargeTypeId: estimate.chargeTypeId,
                            chargeName: estimate.typeOfService,
                            chargeDate: new Date(),
                            numberOfSessions: estimate.numberOfSessions,
                            baseCharge: estimate.baseCharge,
                            tax: estimate.tax,
                            totalCharge: estimate.totalCharge,
                            comment: estimate.comments,
                            discount: estimate.discount,
                            defaultCharge: 0
                        };

                        $rootScope.formatDateTime(newServiceBill, 'chargeDate');

                        $scope.pc.patientDetails.patientBillingDetails.patientChargeDetails.unshift(newServiceBill);
                    });

                    $scope.pc.calculateMonetoryValue();
                    $scope.pc.updateBillingDetails();
                }
            });
        }

        $scope.pc.updateBillingDetails = function () {
            blockUI.start();

            $scope.pc.patientDetails.patientBillingDetails.patientChargeDetails.forEach(function (detail) {
                delete detail.defaultCharge;
            });

            $scope.pc.patientDetails.patientBillingDetails.patientProductDetails.forEach(function (detail) {
                delete detail.defaultCharge;
            });

            $scope.pc.patientDetails.patientBillingDetails.patientPaymentDetails.forEach(function (detail) {
                delete detail.defaultCharge;
            });

            var savePatientCharges = PatientService.savePatientCharges($scope.pc.patientDetails.patientBillingDetails.patientChargeDetails);

            var savePatientProducts = PatientService.savePatientProducts($scope.pc.patientDetails.patientBillingDetails.patientProductDetails);

            var savePatientPayments = PatientService.savePatientPayments($scope.pc.patientDetails.patientBillingDetails.patientPaymentDetails);

            $q.all([savePatientCharges, savePatientProducts, savePatientPayments]).then(function (arrayOfResults) {
                $scope.pc.assignDefaultCharge(arrayOfResults[0].data);

                $scope.pc.assignDefaultCharge(arrayOfResults[1].data);

                $scope.pc.assignDefaultCharge(arrayOfResults[2].data);

                $scope.pc.patientDetails.patientBillingDetails.patientChargeDetails = new kendo.data.ObservableArray(arrayOfResults[0].data);

                $scope.pc.patientDetails.patientBillingDetails.patientProductDetails = new kendo.data.ObservableArray(arrayOfResults[1].data);

                $scope.pc.patientDetails.patientBillingDetails.patientPaymentDetails = new kendo.data.ObservableArray(arrayOfResults[2].data);

                blockUI.stop();

                LoggerService.logSuccess("Billing updated successfully.");
            });
        }

        $scope.pc.addProcedure = function ($event) {
            $event.stopPropagation();

            $event.preventDefault();

            var modalInstance;

            modalInstance = $modal.open({
                templateUrl: "views/modal/addproceduremodalwindow.html",
                controller: 'AddProcedureModalInstanceController',
                windowClass: 'addprocedure-modal-window'
            }), modalInstance.result.then(function (data) {
                var newProcedure = {
                    patientId: $scope.pc.patientDetails.patientId,
                    patientProcedureId: 0,
                    procedureId: data.procedureId,
                    ProcedureDate: new Date(),
                    procedureName: data.procedureName,
                    TreatmentSequenceId: 0
                }

                $rootScope.formatDateTime(newProcedure, 'ProcedureDate');

                $scope.pc.patientDetails.patientProcedures.unshift(newProcedure);
            });
        }

        $scope.pc.updateProcedure = function () {
            $scope.pc.selectedProcedure.ProcedureDate = kendo.toString(kendo.parseDate($scope.pc.selectedProcedure.ProcedureDate, 'MM-dd-yyyy'), 'MM-dd-yyyy HH:mm:ss');

            $scope.promise = PatientService.updateProcedureDetails($scope.pc.selectedProcedure);

            $scope.promise.success(function (data) {

                $scope.pc.selectedProcedure = data;

                LoggerService.logSuccess("Procedure updated successfully.");
            }).error(processErrors);
        }

        $scope.pc.printProcedure = function (dataItem, $event) {
            if (angular.isUndefined($scope.pc.selectedProcedure) || $scope.pc.selectedProcedure.patientProcedureId != dataItem.patientProcedureId) {
                var grid = $scope.pc.mappingdatagrid;

                grid.items().each(function () {
                    var data = grid.dataItem(this);

                    if (data.uid == dataItem.uid) {
                        grid.select(this);
                    }
                });
            }

            $timeout(function () {
                $scope.promise = PatientService.getProcedurePrintForm(dataItem.procedureName);

                $scope.promise.success(function (form) {
                    var compiledHTML = $compile(form)($scope);

                    $timeout(function () {
                        var returnString = '';

                        for (i = 0; i < compiledHTML.length; i++)
                            returnString += compiledHTML[i].outerHTML;

                        returnString = replaceAll('undefined', '', returnString);

                        var popupWin = window.open('', '_blank', 'width: 100%,height: 100%');

                        popupWin.document.open("text/html");

                        popupWin.document.write(returnString);

                        popupWin.document.close();

                        $timeout(function () { 
                        	popupWin.print(); 
                        	popupWin.onfocus = function () {
                                popupWin.close();
                            }
                        
                        },300);

                    }, 100);
                });
            }, 500);
        }

        $scope.pc.deleteProcedure = function (dataItem) {
            var modalInstance;

            modalInstance = $modal.open({
                templateUrl: "views/modal/confirmmodalwindow.html",
                controller: 'ConfirmModalInstanceController'
            }), modalInstance.result.then(function () {
                $scope.promise = PatientService.deleteProcedure(dataItem.patientProcedureId);

                $scope.promise.success(function (data) {
                    if (data == 'true') {
                        $scope.pc.patientDetails.patientProcedures.remove(dataItem);

                        LoggerService.logSuccess("Procedure deleted successfully.");
                    }
                    else {
                        LoggerService.logError("Procedure deletion failed.");
                    }
                }).error(processErrors);
            });
        }

        $scope.pc.addNewPatient = function () {
            $scope.pc.patientRowSelected = false;

            if ($scope.pc.isCollapsed) {
                $scope.pc.toggleResults();
            }

            $scope.pc.patientDetails = {};

            $scope.pc.currentTab = 'PatientTab';

            $scope.pc.patientDetails = { patientId: 0 };
        }

        $scope.pc.updatePatientDetails = function () {

            $scope.pc.patientDetails.appointment = kendo.toString(kendo.parseDate($scope.pc.patientDetails.appointment, 'yyyy-MM-ddTHH:mm:ss.fffZ'), 'MM-dd-yyyy HH:mm:ss');

            delete $scope.pc.patientDetails.patientBillingDetails;

            $scope.promise = PatientService.saveOrUpdatePatient($scope.pc.patientDetails);

            $scope.promise.success(function (data) {
                processPatient(data);

                LoggerService.logSuccess('Patient updated successfully.');
            }).error(processErrors);
        }

        $scope.pc.printPatientConsentForm = function () {
            var modalInstance;

            modalInstance = $modal.open({
                templateUrl: "views/modal/consentmodalwindow.html",
                controller: 'ConsentModalInstanceController',
                windowClass: 'consent-modal-window',
                resolve: {
                    patientName: function () {
                        return $scope.pc.patientDetails.firstName + ' ' + $scope.pc.patientDetails.lastName;
                    }
                }
            }), modalInstance.result.then(function (data) {

            });
        }

        $scope.pc.deletePatient = function () {
            var modalInstance;

            modalInstance = $modal.open({
                templateUrl: "views/modal/confirmmodalwindow.html",
                controller: 'ConfirmModalInstanceController'
            }), modalInstance.result.then(function () {
                $scope.promise = PatientService.deletePatient($scope.pc.patientDetails.patientId);

                $scope.promise.success(function (data) {

                }).error(processErrors);
            });
        }

        $scope.pc.calculateMonetoryValue = function () {
            $scope.pc.totalChargeForServices = kendo.parseFloat($rootScope.sum($scope.pc.patientDetails.patientBillingDetails.patientChargeDetails, 'totalCharge'));

            $scope.pc.totalChargeForProducts = kendo.parseFloat($rootScope.sum($scope.pc.patientDetails.patientBillingDetails.patientProductDetails, 'productTotalCharge'));

            $scope.pc.totalChargePaidForServices = 0;

            $scope.pc.totalChargePaidForProducts = 0;

            $scope.pc.patientDetails.patientBillingDetails.patientPaymentDetails.forEach(function (payment) {
                if (payment.creditApplicationName == 'Product') {
                    $scope.pc.totalChargePaidForProducts += kendo.parseFloat(payment.creditAmount);
                } else if (payment.creditApplicationName == 'Service') {
                    $scope.pc.totalChargePaidForServices += kendo.parseFloat(payment.creditAmount);
                }
            });

            $scope.pc.totalBalanceForServices = $scope.pc.totalChargeForServices - $scope.pc.totalChargePaidForServices;

            $scope.pc.totalBalanceForProducts = $scope.pc.totalChargeForProducts - $scope.pc.totalChargePaidForProducts;

            $scope.pc.totalCharge = $scope.pc.totalChargeForServices + $scope.pc.totalChargeForProducts;

            $scope.pc.totalChargePaid = $scope.pc.totalChargePaidForServices + $scope.pc.totalChargePaidForProducts;

            $scope.pc.totalBalance = $scope.pc.totalBalanceForServices + $scope.pc.totalBalanceForProducts;
        }

        $scope.pc.assignPatientId = function (entry) {
            entry.patientId = $scope.pc.patientDetails.patientId;
        }

        $scope.pc.uploadImage = function () {
            $scope.promise = PatientService.uploadPatientImage($scope.pc.imageFile, $scope.pc.patientDetails.patientId, $scope.pc.imageDescription, $scope.pc.imagefileName, $scope.pc.isProfileImage);

            $scope.promise.success(function (data) {
                $scope.pc.patientDetails.pictures.unshift(data);

                $scope.pc.imageFile = '';

                $scope.pc.imageDescription = ''

                angular.element(document.getElementById('uploadedfile')).val(null);

                LoggerService.logSuccess('Image uploaded successfully.');
            }).error(processErrors);
        }

        $scope.pc.compareImages = function ($event) {
            $event.stopPropagation();
            $event.preventDefault();

            var rows = $scope.pc.picturesdatagrid.select();

            var dataItems = [];

            for (var i = 0; i < rows.length; i++) {
                dataItems.push($scope.pc.picturesdatagrid.dataItem(rows[i]));
            }

            var modalInstance;

            var windowClass;

            var modalWindowHtml;

            if (dataItems.length == 1) {
                windowClass = 'showsingleimage-modal-window';
                modalWindowHtml = 'showsingleimage.html';
            }
            else {
                windowClass = 'compareimages-modal-window';
                modalWindowHtml = 'compareimages.html';
            }

            modalInstance = $modal.open({
                templateUrl: "views/modal/" + modalWindowHtml,
                controller: 'CompareImagesController',
                windowClass: windowClass,
                resolve: {
                    images: function () {
                        return dataItems;
                    }
                }
            });
        }

        $scope.pc.assignDefaultCharge = function (entry) {
            if (entry.length > 0) {
                entry.forEach(function (detail) {
                    detail.defaultCharge = 0;
                });
            }
        }

        $scope.pc.printInvoice = function () {
            var modalInstance;

            modalInstance = $modal.open({
                templateUrl: "views/modal/datepickersforinvoice.html",
                controller: 'DatePickersModalInstanceController',
                windowClass: 'datepickers-modal-window',
                resolve: {
                    estimates: function () {
                        return $scope.pc.patientDetails.patientEstimates;
                    },
                    patientName: function () {
                        return $scope.pc.patientDetails.firstName + ' ' + $scope.pc.patientDetails.lastName;
                    }
                }
            }), modalInstance.result.then(function (data) {
                $scope.pc.billingPeriodServiceCharges = 0;

                $scope.pc.billingPeriodProductCharges = 0;

                $scope.pc.billingPeriodPaymentDone = 0;

                $scope.pc.startDate = data.startDate;

                $scope.pc.endDate = data.endDate;

                $scope.pc.billingPeriodServices = [];

                $scope.pc.billingPeriodProducts = [];

                $scope.pc.billingPeriodPayments = [];

                $scope.pc.patientDetails.patientBillingDetails.patientChargeDetails.forEach(function (detail) {
                    if (kendo.parseDate(detail.chargeDate, 'MM-dd-yyyy') >= kendo.parseDate(data.startDate, 'MM-dd-yyyy') && kendo.parseDate(detail.chargeDate, 'MM-dd-yyyy') <= kendo.parseDate(data.endDate, 'MM-dd-yyyy')) {
                        $scope.pc.billingPeriodServiceCharges += kendo.parseFloat(detail.totalCharge);

                        $scope.pc.billingPeriodServices.push(detail);
                    }
                });

                $scope.pc.patientDetails.patientBillingDetails.patientProductDetails.forEach(function (detail) {
                    if (kendo.parseDate(detail.date, 'MM-dd-yyyy') >= kendo.parseDate(data.startDate, 'MM-dd-yyyy') && kendo.parseDate(detail.date, 'MM-dd-yyyy') <= kendo.parseDate(data.endDate, 'MM-dd-yyyy')) {
                        $scope.pc.billingPeriodProductCharges += kendo.parseFloat(detail.productTotalCharge);

                        $scope.pc.billingPeriodProducts.push(detail);
                    }
                });

                $scope.pc.patientDetails.patientBillingDetails.patientPaymentDetails.forEach(function (detail) {
                    if (kendo.parseDate(detail.creditDate, 'MM-dd-yyyy') >= kendo.parseDate(data.startDate, 'MM-dd-yyyy') && kendo.parseDate(detail.creditDate, 'MM-dd-yyyy') <= kendo.parseDate(data.endDate, 'MM-dd-yyyy')) {
                        $scope.pc.billingPeriodPaymentDone += kendo.parseFloat(detail.creditAmount);

                        $scope.pc.billingPeriodPayments.push(detail);
                    }
                });

                $scope.pc.billingPeriodTotalCharges = kendo.parseFloat($scope.pc.billingPeriodServiceCharges) + kendo.parseFloat($scope.pc.billingPeriodProductCharges);

                $scope.pc.billingPeriodBalance = kendo.parseFloat($scope.pc.billingPeriodTotalCharges) - kendo.parseFloat($scope.pc.billingPeriodPaymentDone);

                var bareform = $http.get('bootstraptest.html');

                bareform.success(function (form) {
                    var compiledHTML = $compile(form)($scope);

                    $timeout(function () {
                        var returnString = '';

                        for (i = 0; i < compiledHTML.length; i++)
                            returnString += compiledHTML[i].outerHTML;

                        returnString = replaceAll('undefined', '', returnString);

                        var popupWin = window.open('', '_blank', 'width: 100%,height: 100%');

                        popupWin.document.open("text/html");

                        popupWin.document.write(returnString);

                        popupWin.document.close();

                        $timeout(function () { 
                        	popupWin.print(); 
                        	popupWin.onfocus = function () {
                                popupWin.close();
                            }
                        
                        },300);

                        
                    }, 100);
                });
            });
        }

        $scope.pc.deleteImage = function (dataItem) {
            selectRow($scope.pc.picturesdatagrid, dataItem);

            var modalInstance;

            modalInstance = $modal.open({
                templateUrl: 'views/modal/confirmmodalwindow.html',
                controller: 'ConfirmModalInstanceController'
            }), modalInstance.result.then(function () {
                var patientImagesIds = [];

                patientImagesIds.push(dataItem.patientImagesID);

                $scope.promise = PatientService.deletePatientImages(patientImagesIds);

                $scope.promise.success(function () {
                    var dataToBeRemoved = '';

                    $scope.pc.patientDetails.pictures.forEach(function (picture) {
                        if (picture.patientImagesID == dataItem.patientImagesID) {
                            dataToBeRemoved = picture;
                        }
                    });

                    $scope.pc.patientDetails.pictures.splice($scope.pc.patientDetails.pictures.indexOf(dataToBeRemoved), 1);
                });
            });
        }
    }]);