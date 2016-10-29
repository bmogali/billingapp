appServices.factory("TaskStorageService", function () {
    var DEMO_TASKS, STORAGE_ID;
    return STORAGE_ID = "tasks", DEMO_TASKS = '[ {"title": "Finish homework", "completed": true}, {"title": "Try Google glass", "completed": false}, {"title": "Make a call", "completed": true}, {"title": "Build a snowman :)", "completed": false}, {"title": "Apply for monster university!", "completed": false}, {"title": "Play games with friends", "completed": true}, {"title": "Learn Swift", "completed": false}, {"title": "Shopping", "completed": false} ]', {
        get: function () {
            return JSON.parse(localStorage.getItem(STORAGE_ID) || DEMO_TASKS)
        },
        put: function (tasks) {
            return localStorage.setItem(STORAGE_ID, JSON.stringify(tasks))
        }
    }
});