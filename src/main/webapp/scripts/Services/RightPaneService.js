appServices.factory('RightPaneService', ['$http', 'ApiBaseUrl', function ($http, ApiBaseUrl) {
    return {
        getRightPaneForm: function (formName) {
            return $http.get('./views/rightpaneforms/' + formName + '.html');
        }
    }
}]);