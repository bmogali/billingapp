appDirectives
    .directive("uiTime", [function () {
        return {
            restrict: "A",
            link: function (scope, ele) {
                var checkTime, startTime;
                return startTime = function () {
                    var h, m, s, t, time, today;
                    return today = new Date, h = today.getHours(), m = today.getMinutes(), s = today.getSeconds(), m = checkTime(m), s = checkTime(s), time = h + ":" + m + ":" + s, ele.html(time), t = setTimeout(startTime, 500)
                }, checkTime = function (i) {
                    return 10 > i && (i = "0" + i), i
                }, startTime()
            }
        }
    }])
    .directive("uiNotCloseOnClick", [function () {
        return {
            restrict: "A",
            compile: function (ele) {
                return ele.on("click", function (event) {
                    return event.stopPropagation()
                })
            }
        }
    }])
    .directive("slimScroll", [function () {
        return {
            restrict: "A",
            link: function (scope, ele, attrs) {
                return ele.slimScroll({
                    height: attrs.scrollHeight || "100%"
                })
            }
        }
    }])
    .directive("i18n", ["LocalizationService", function (localize) {
        var i18nDirective;
        return i18nDirective = {
            restrict: "EA",
            updateText: function (ele, input, placeholder) {
                var result;
                return result = void 0, "i18n-placeholder" === input ? (result = localize.getLocalizedString(placeholder), ele.attr("placeholder", result)) : input.length >= 1 ? (result = localize.getLocalizedString(input), ele.text(result)) : void 0
            },
            link: function (scope, ele, attrs) {
                return scope.$on("localizeResourcesUpdated", function () {
                    return i18nDirective.updateText(ele, attrs.i18n, attrs.placeholder)
                }), attrs.$observe("i18n", function (value) {
                    return i18nDirective.updateText(ele, value, attrs.placeholder)
                })
            }
        }
    }])
    .directive("customPage", function () {
        return {
            restrict: "A",
            controller: ["$scope", "$element", "$location", function ($scope, $element, $location) {
                var addBg, path;
                return path = function () {
                    return $location.path()
                }, addBg = function (path) {
                    switch ($element.removeClass("body-wide body-lock"), path) {
                        case "/404":
                        case "/500":
                        case "/signin":
                        case "/signup":
                        case "/forgot-password":
                            return $element.addClass("body-wide");
                        case "/pages/lock-screen":
                            return $element.addClass("body-wide body-lock")
                    }
                }, addBg($location.path()), $scope.$watch(path, function (newVal, oldVal) {
                    return newVal !== oldVal ? addBg($location.path()) : void 0
                })
            }]
        }
    })
    .directive("toggleNavCollapsedMin", ["$rootScope", function ($rootScope) {
        return {
            restrict: "A",
            link: function (scope, ele) {
                var app;
                return app = $("#app"), ele.on("click", function (e) {
                    return app.hasClass("nav-collapsed-min") ? app.removeClass("nav-collapsed-min") : (app.addClass("nav-collapsed-min"), $rootScope.$broadcast("nav:reset")), e.preventDefault()
                })
            }
        }
    }])
    .directive("collapseNav", [function () {
        return {
            restrict: "A",
            link: function (scope, ele) {
                var $a, $aRest, $app, $lists, $listsRest, $nav, $window, Timer, prevWidth, updateClass;
                return $window = $(window), $lists = ele.find("ul").parent("li"), $lists.append('<i class="fa fa-angle-down icon-has-ul-h"></i><i class="fa fa-angle-right icon-has-ul"></i>'), $a = $lists.children("a"), $listsRest = ele.children("li").not($lists), $aRest = $listsRest.children("a"), $app = $("#app"), $nav = $("#nav-container"), $a.on("click", function (event) {
                    var $parent, $this;
                    return $app.hasClass("nav-collapsed-min") || $nav.hasClass("nav-horizontal") && $window.width() >= 768 ? !1 : ($this = $(this), $parent = $this.parent("li"), $lists.not($parent).removeClass("open").find("ul").slideUp(), $parent.toggleClass("open").find("ul").stop().slideToggle(), event.preventDefault())
                }), $aRest.on("click", function () {
                    return $lists.removeClass("open").find("ul").slideUp()
                }), scope.$on("nav:reset", function () {
                    return $lists.removeClass("open").find("ul").slideUp()
                }), Timer = void 0, prevWidth = $window.width(), updateClass = function () {
                    var currentWidth;
                    return currentWidth = $window.width(), 768 > currentWidth && $app.removeClass("nav-collapsed-min"), 768 > prevWidth && currentWidth >= 768 && $nav.hasClass("nav-horizontal") && $lists.removeClass("open").find("ul").slideUp(), prevWidth = currentWidth
                }, $window.resize(function () {
                    var t;
                    return clearTimeout(t), t = setTimeout(updateClass, 300)
                })
            }
        }
    }])
    .directive("highlightActive", [function () {
        return {
            restrict: "A",
            controller: ["$scope", "$element", "$attrs", "$location", function ($scope, $element, $attrs, $location) {
                var highlightActive, links, path;
                return links = $element.find("a"), path = function () {
                    return $location.path()
                }, highlightActive = function (links, path) {
                    return path = "#" + path, angular.forEach(links, function (link) {
                        var $li, $link, href;
                        return $link = angular.element(link), $li = $link.parent("li"), href = $link.attr("href"), $li.hasClass("active") && $li.removeClass("active"), 0 === path.indexOf(href) ? $li.addClass("active") : void 0
                    })
                }, highlightActive(links, $location.path()), $scope.$watch(path, function (newVal, oldVal) {
                    return newVal !== oldVal ? highlightActive(links, $location.path()) : void 0
                })
            }]
        }
    }])
    .directive("toggleOffCanvas", [function () {
        return {
            restrict: "A",
            link: function (scope, ele) {
                return ele.on("click", function () {
                    return $("#app").toggleClass("on-canvas")
                })
            }
        }
    }])
    .directive('validSubmit', ['$parse', function ($parse) {
        return {
            require: 'form',
            link: function (scope, element, iAttrs, form) {
                form.$submitted = false;
                var fn = $parse(iAttrs.validSubmit);
                element.on('submit', function (event) {
                    scope.$apply(function () {                    
                        form.$submitted = true;
                        if (form.$valid) {
                            fn(scope, { $event: event });
                            form.$submitted = false;
                        }
                    });
                });
            }
        };
    }])
    .directive('ngEnter', function () {
        return function (scope, element, attrs) {
            element.bind("keydown keypress", function (event) {
                if (event.which === 13) {
                    scope.$apply(function () {
                        scope.$eval(attrs.ngEnter);
                    });
                    event.preventDefault();
                }
            });
        };
    })