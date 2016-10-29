patientBillingApp.controller('DatePickersModalInstanceController', ['$scope', '$modalInstance',
    function ($scope, $modalInstance) {
        $scope.dpmic = {};

        var pastDate = new Date(new Date().setMonth(new Date().getMonth() - 1));

        $scope.dpmic.startDate = kendo.toString(kendo.parseDate(pastDate, 'MM-dd-yyyy'), 'MM-dd-yyyy');

        $scope.dpmic.endDate = kendo.toString(kendo.parseDate(new Date(), 'MM-dd-yyyy'), 'MM-dd-yyyy');;

        $scope.dpmic.print = function () {
            var entry = {};

            entry.startDate = $scope.dpmic.startDate;

            entry.endDate = $scope.dpmic.endDate;

            $modalInstance.close(entry);
        }

        $scope.dpmic.cancel = function () {
            $modalInstance.dismiss('cancel');
        }
    }]);