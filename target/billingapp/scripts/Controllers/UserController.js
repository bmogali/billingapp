patientBillingApp.controller('UserController', ['$scope', '$window', '$location', 'UserService', 'AuthenticationService', 'LoggerService', 'BrandName',
    function ($scope, $window, $location, UserService, AuthenticationService, LoggerService, BrandName) {

        if (AuthenticationService.isAuthenticated == true) {
            $location.path("/dashboard");
        }

        $scope.uc = {};

        $scope.uc.BrandName = BrandName;

        $scope.uc.signIn = function () {
            $scope.promise = UserService.signIn($scope.uc.username, $scope.uc.password);

            $scope.promise.success(function (data, status, headers, config) {
                AuthenticationService.isAuthenticated = true;
                $window.sessionStorage.token = headers()["x-auth-token"];
                $window.sessionStorage.username = $scope.uc.username;
                $location.path("/");
            }).error(function () {
                LoggerService.logError("Invalid credentials.");
            });
        };

        $scope.uc.signout = function () {
            $scope.promise = UserService.signOut($scope.uc.username, $scope.uc.password);
            $scope.promise.success();
            AuthenticationService.isAuthenticated = false;
            delete $window.sessionStorage.token;
            $location.path("/signin");
        }
    }]);