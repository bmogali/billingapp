appDirectives.directive('rightPane', ['$rootScope', '$compile', 'RightPaneService',
    function ($rootScope, $compile,RightPaneService) {
        function replaceAll(find, replace, str) {
            return str.replace(new RegExp(find, 'g'), replace);
        }

        return {
            restrict: 'E',
            link: function (scope, element, attrs) {
                scope.$watch('pc.currentTab', function () {
                    RightPaneService.getRightPaneForm(scope.pc.currentTab).success(function (htmlform) {
                        element.empty();
                        element.append($compile(htmlform)(scope));
                    });
                });
            }
        };
    }]);