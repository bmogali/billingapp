patientBillingApp.controller('UserProfileController', ['$scope', '$window', 'UserService', 'LoggerService', function ($scope, $window, UserService, LoggerService) {

    $scope.upc = {};

    $scope.promise = UserService.getUserInfo();

    $scope.promise.success(function (data) {
        if (data.StandardOutput.Status) {
            $scope.upc.RequestedUser = data.RequestedUser;
        }
        else {
            LoggerService.logError(data.StandardOutput.Errors[0].ErrorMessage);
        }
    }).error(function (data, status) {
        LoggerService.logError("Network error.");
    });

    $scope.upc.updateUserInfo = function () {

        UserService.userInfoDirty = true;

        $scope.promise = UserService.updateUserInfo($scope.upc.RequestedUser);

        $scope.promise.success(function (data) {
            if (data.StandardOutput.Status) {
                LoggerService.logSuccess("User Info Updated.");
            }
            else {
                LoggerService.logError(data.StandardOutput.Errors[0].ErrorMessage);
            }
        }).error(function (data, status) {
            LoggerService.logError("Network error.");
        });
    }
}]);