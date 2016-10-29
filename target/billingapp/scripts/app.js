var patientBillingApp = angular.module('patientBillingApp', ['ngRoute', 'appControllers', 'appServices', 'appDirectives', 'blockUI', 'ui.bootstrap', 'kendo.directives', 'mwl.calendar']);

patientBillingApp.value("BrandName", "Derma Laser Center");

patientBillingApp.value("ApiBaseUrl", "http://localhost:8080/billingapp/services/rest/");

var appControllers = angular.module('appControllers', []);

var appDirectives = angular.module('appDirectives', []);

var appServices = angular.module('appServices', []);

patientBillingApp.config(['$locationProvider', '$httpProvider', '$routeProvider', 'blockUIConfig', function ($location, $httpProvider, $routeProvider, blockUIConfig) {

    $httpProvider.interceptors.push('AuthInterceptorService');

    blockUIConfig.template = '<div class=\"block-ui-overlay\"></div><div class=\"block-ui-message-container\" aria-live=\"assertive\" aria-atomic=\"true\"><img src=\"images/loader.gif\" /></div>';

    $routeProvider.
        when('/', {
            templateUrl: 'views/dashboard.html',
            controller: 'DashboardController',
            access: { requiredAuthentication: true }
        }).
        when('/searchpatients', {
            templateUrl: 'views/forms/searchpatients.html',
            controller: 'PatientController',
            access: { requiredAuthentication: true }
        }).
        when('/dashboard', {
            templateUrl: 'views/dashboard.html',
            controller: 'DashboardController',
            access: { requiredAuthentication: true }
        }).
        when('/signin', {
            templateUrl: 'views/pages/signin.html',
            controller: 'UserController'
        }).
        when('/profile', {
            templateUrl: 'views/pages/profile.html',
            controller: 'UserProfileController',
            access: { requiredAuthentication: true }
        }).
        when('/setupservices', {
            templateUrl: 'views/setuppages/setupservices.html',
            controller: 'SetupController',
            access: { requiredAuthentication: true }
        }).
        when('/setupproducts', {
            templateUrl: 'views/setuppages/setupproducts.html',
            controller: 'SetupController',
            access: { requiredAuthentication: true }
        }).
        when('/setuppaymenttypes', {
            templateUrl: 'views/setuppages/setuppaymenttypes.html',
            controller: 'SetupController',
            access: { requiredAuthentication: true }
        }).
        when('/setuptaxes', {
            templateUrl: 'views/setuppages/setuptaxes.html',
            controller: 'SetupController',
            access: { requiredAuthentication: true }
        }).
        when('/reports', {
            templateUrl: 'views/reports/accountreports.html',
            controller: 'ReportsController',
            access: { requiredAuthentication: true }
        }).
        otherwise({
            redirectTo: '/'
        });
}]);

patientBillingApp.run(function ($rootScope, $location, $window, AuthenticationService) {
    $rootScope.$on("$routeChangeStart", function (event, nextRoute, currentRoute) {
        if (nextRoute != null
            && nextRoute.access != null
            && nextRoute.access.requiredAuthentication
            && !AuthenticationService.isAuthenticated
            && !$window.sessionStorage.token) {
            $location.path("/signin");
        }
    });

    $rootScope.sum = function (items, prop) {
        var total = 0;

        for (var i = 0, _len = items.length; i < _len; i++) {
            total = total + kendo.parseFloat(items[i][prop]);
        }

        return total;
    };

    $rootScope.formatDateTime = function (entry, property) {
        if (kendo.parseDate(entry[property], 'yyyy-MM-ddTHH:mm:ss.fffZ') != null) {
            entry[property] = kendo.toString(kendo.parseDate(entry[property], 'yyyy-MM-ddTHH:mm:ss.fffZ'), 'MM-dd-yyyy hh:mm:ss');
        }
    }
});

function replaceAll(find, replace, str) {
    return str.replace(new RegExp(find, 'g'), replace);
}

function skipNonEditableCellsInGrid(e, grid) {
    var current = grid.current();

    if (e.keyCode == 9) {

        e.stopPropagation();

        e.preventDefault();

        if (e.shiftKey) {
            if (!current.hasClass("editable-cell")) {
                var nextCell = current.prevAll(".editable-cell");
                if (!nextCell[0]) {
                    var nextRow = current.parent().prev();
                    var nextCell = current.parent().prev().children(".editable-cell:first");
                }
                grid.current(nextCell);
                grid.editCell(nextCell[0]);
            }
        }
        else {
            if (!current.hasClass("editable-cell")) {
                var nextCell = current.nextAll(".editable-cell");
                if (!nextCell[0]) {
                    var nextRow = current.parent().next();
                    var nextCell = current.parent().next().children(".editable-cell:first");
                }
                grid.current(nextCell);
                grid.editCell(nextCell[0]);
            }
        }
    }
}

function selectRow(grid, dataItem) {
    grid.items().each(function () {
        var data = grid.dataItem(this);

        if (data.uid == dataItem.uid) {
            grid.select(this);
        }
    });
}

function selectRowUsingNavigation(grid, e) {
    var arrows = [38, 40];
    if (arrows.indexOf(e.keyCode) >= 0) {
        setTimeout(function () {
            grid.select($("[id$='_active_cell']").closest("tr"));
        });
    }
}