patientBillingApp.controller('AddProcedureModalInstanceController', ['$scope', '$modalInstance', 'PatientService', function ($scope, $modalInstance, PatientService) {

    $scope.apmic = {};

    $scope.apmic.selectedProcedure = '';

    $scope.apmic.okButtonDisabled = function () {
        if ($scope.apmic.selectedProcedure == '')
            return true;

        if ($scope.apmic.selectedProcedure == null)
            return true;

        return false;
    }

    PatientService.getAllProcedures().success(function (data) {
        $scope.apmic.proceduresMasterData = data;
    });

    $scope.apmic.ok = function () {
        $modalInstance.close($scope.apmic.selectedProcedure);
    };

    $scope.apmic.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}]);