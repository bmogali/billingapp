appServices.factory('AuthInterceptorService', ['$q', '$window', '$location',
    function ($q, $window, $location) {

    var authInterceptorServiceFactory = {};

    var _request = function (config) {

        config.headers = config.headers || {};

        if ($window.sessionStorage.token) {
            config.headers["x-auth-token"] = $window.sessionStorage.token;
        }

        return config;
    }

    var _responseError = function (rejection) {
        if (rejection.status === 401) {
            $location.path('/signin');
        }
        return $q.reject(rejection);
    }

    authInterceptorServiceFactory.request = _request;

    authInterceptorServiceFactory.responseError = _responseError;

    return authInterceptorServiceFactory;
}]);