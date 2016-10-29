appServices.factory('UserService', ['$http', 'ApiBaseUrl', function ($http, ApiBaseUrl) {
    return {
        signIn: function (username, password) {
            return $http.post(ApiBaseUrl + 'user/authenticate', "", { headers: { 'X-Username': username, 'X-Password': password } });
        },

        signOut: function () {
            return $http.post(ApiBaseUrl + 'user/logout', "");
        }
    }
}]);