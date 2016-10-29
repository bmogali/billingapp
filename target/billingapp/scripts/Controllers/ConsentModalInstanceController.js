patientBillingApp.controller('ConsentModalInstanceController', ['$scope', '$compile', '$timeout', '$modalInstance', 'patientName', 'PatientService',
    function ($scope, $compile, $timeout, $modalInstance, patientName, PatientService) {

        $scope.cmic = {};

        $scope.cmic.selectedForm = 'FatTransferConsentForm';

        $scope.cmic.patientName = patientName;

        $scope.cmic.print = function () {
            $scope.promise = PatientService.getConsentForm($scope.cmic.selectedForm);

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

                    popupWin.print();

                    popupWin.onfocus = function () {
                        popupWin.close();
                    }
                }, 100);

                $modalInstance.close();
            });
        };

        $scope.cmic.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }]);