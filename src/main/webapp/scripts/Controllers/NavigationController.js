patientBillingApp.controller("NavigationController", ["$scope", "TaskStorageService", "filterFilter", function ($scope, taskStorage, filterFilter) {
    var tasks;
    return tasks = $scope.tasks = taskStorage.get(), $scope.taskRemainingCount = filterFilter(tasks, {
        completed: !1
    }).length, $scope.$on("taskRemaining:changed", function (event, count) {
        return $scope.taskRemainingCount = count
    })
}])