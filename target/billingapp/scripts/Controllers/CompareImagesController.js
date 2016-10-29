patientBillingApp.controller('CompareImagesController', ['$scope', '$modalInstance', 'images',
    function ($scope, $modalInstance, images) {
        $scope.cic = {};

        $scope.cic.firstImage = images[0];

        $scope.cic.secondImage = images[1];

        $scope.cic.ok = function () {
            $modalInstance.close();
        };
    }]);