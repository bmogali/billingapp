appServices.factory('PatientService', ['$http', 'ApiBaseUrl',
    function ($http, ApiBaseUrl) {
        var selectedPatientId = 0;

        return {
            searchPatients: function (searchCriteria, options) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'searchPatients',
                    data: $.param({ patientSearchCriteria: searchCriteria }),
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                });
            },

            getPatientDetails: function (patientId) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'getPatientDetail',
                    data: $.param({ patientId: patientId }),
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                });
            },

            saveOrUpdatePatient: function (patientDetails) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'saveOrUpdatePatient',
                    data: patientDetails,
                    headers: { 'Content-Type': 'application/json' }
                });
            },

            deletePatient: function (patientId) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'deletePatient',
                    data: $.param({ patientId: patientId }),
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                });
            },

            getPatientImages: function (patientId) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'getPatientImages',
                    data: $.param({ patientId: patientId }),
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                });
            },

            uploadPatientImage: function (imageFile, patientId, description, fileName, profileImage) {

                var fd = new FormData();
                fd.append('file', imageFile);
                fd.append('patientId', patientId);
                fd.append('imageDescription', description);
                fd.append('fileName', fileName);
                fd.append('profile', profileImage);

                return $http.post(ApiBaseUrl + 'uploadPatientImage', fd, {
                    transformRequest: angular.identity,
                    headers: { 'Content-Type': undefined }
                });
            },

            deletePatientImages: function (patientImagesIds) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'deletePatientImages',
                    data: patientImagesIds,
                    headers: { 'Content-Type': 'application/json' }
                });
            },

            getProcedureDetails: function (patientProcedureId) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'getPatientProcedure',
                    data: $.param({ patientProcedureId: patientProcedureId }),
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                });
            },

            updateProcedureDetails: function (procedureDetails) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'savePatientProcedure',
                    data: procedureDetails,
                    headers: { 'Content-Type': 'application/json' }
                });
            },

            deleteProcedure: function (patientProcedureId) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'deletePatientProcedure',
                    data: $.param({ patientProcedureId: patientProcedureId }),
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                });
            },

            getAllProcedures: function () {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'searchProcedures',
                    data: $.param({ procedureSearchCriteria: '' }),
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                });
            },

            getAllServices: function () {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'getAllServices',
                    data: $.param({ '': '' }),
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                });
            },

            saveServices: function (services) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'saveServices',
                    data: services,
                    headers: { 'Content-Type': 'application/json' }
                });
            },

            getAllProducts: function () {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'getAllProducts',
                    data: $.param({ '': '' }),
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                });
            },

            saveProducts: function (products) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'saveProducts',
                    data: products,
                    headers: { 'Content-Type': 'application/json' }
                });
            },

            getAllCreditTypes: function () {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'getAllCreditTypes',
                    data: $.param({ '': '' }),
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                });
            },

            getAllCreditApplications: function () {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'getAllCreditApplications',
                    data: $.param({ '': '' }),
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                });
            },

            saveCreditTypes: function (creditTypes) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'saveCreditTypes',
                    data: creditTypes,
                    headers: { 'Content-Type': 'application/json' }
                });
            },

            savePatientCharges: function (patientChargeDetails) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'savePatientCharges',
                    data: patientChargeDetails,
                    headers: { 'Content-Type': 'application/json' }
                });
            },

            deletePatientCharges: function (patientChargeIds) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'deletePatientCharges',
                    data: patientChargeIds,
                    headers: { 'Content-Type': 'application/json' }
                });
            },

            savePatientProducts: function (patientProducts) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'savePatientProducts',
                    data: patientProducts,
                    headers: { 'Content-Type': 'application/json' }
                });
            },

            deletePatientProducts: function (patientProductIds) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'deletePatientProducts',
                    data: patientProductIds,
                    headers: { 'Content-Type': 'application/json' }
                });
            },

            savePatientPayments: function (patientPayments) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'savePatientPayments',
                    data: patientPayments,
                    headers: { 'Content-Type': 'application/json' }
                });
            },

            deletePatientPayments: function (patientPaymentIds) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'deletePatientPayments',
                    data: patientPaymentIds,
                    headers: { 'Content-Type': 'application/json' }
                });
            },

            getPatientEstimates: function (patientId) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'getPatientEstimates',
                    data: $.param({ patientId: patientId }),
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                });
            },

            savePatientEstimates: function (estimates) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'savePatientEstimates',
                    data: estimates,
                    headers: { 'Content-Type': 'application/json' }
                });
            },

            getConsentForm: function (formName) {
                return $http.get('./views/printforms/patientforms/' + formName + '.html');
            },

            getAccountReports: function (serviceReportCriteria) {
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'servicesReport',
                    data: serviceReportCriteria,
                    headers: { 'Content-Type': 'application/json' }
                });
            },

            getProcedurePrintForm: function (formName) {
                return $http.get('./views/printforms/newprocedureforms/' + formName + '.html');
            },

            getAppointments: function (month, year) {
                var criteria = {};
                criteria.startDate = month + '-01-' + year + ' 00:00:00';
                criteria.endDate = month + '-31-' + year + ' 23:59:59';

                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'getAppointments',
                    data: criteria,
                    headers: { 'Content-Type': 'application/json' }
                });
            },

            getTaxRates: function(){
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'getTaxRates',
                    data: $.param({ '': '' }),
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                });
            },

            saveTaxRates: function(taxData){
                return $http({
                    method: 'POST',
                    url: ApiBaseUrl + 'saveTaxRates',
                    data: taxData,
                    headers: { 'Content-Type': 'application/json' }
                });
            },

            selectedPatientId: selectedPatientId
        }
    }]);