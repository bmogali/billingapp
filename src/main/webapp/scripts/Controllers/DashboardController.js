patientBillingApp.controller('DashboardController', ['$scope', '$location', '$modal', 'PatientService', 'LoggerService', 'blockUI', 
    function ($scope, $location, $modal, PatientService, LoggerService, blockUI) {
        var getAppointments = function (month, year) {
            blockUI.start();

            PatientService.getAppointments(month, year).success(processAppointments).error(processErrors);
        }

        var processAppointments = function (appointments) {
            $scope.dc.events = [];

            appointments.forEach(function (appointment) {
                var starts_at = kendo.parseDate(appointment.appointment, 'MM-dd-yyyy HH:mm:ss');

                var ends_at = new Date(starts_at.getTime() + 30 * 60000);

                var calendarAppointment = { 'title': appointment.firstName + ' ' + appointment.lastName + ' (' + starts_at.toLocaleTimeString() + ' - ' + ends_at.toLocaleTimeString() + ')', 'type': 'info', starts_at: starts_at, ends_at: ends_at, id: appointment.patientId };

                $scope.dc.events.push(calendarAppointment);
            });

            blockUI.stop();
        }

        var processErrors = function () {
            LoggerService.logError("Network error.");
        }

        var currentYear = moment().year();

        var currentMonth = moment().month();

        var showModal = function (action, event) {
            $modal.open({
                templateUrl: 'modalContent.html',
                controller: function ($scope, $modalInstance) {
                    $scope.$modalInstance = $modalInstance;
                    $scope.action = action;
                    $scope.event = event;                    
                    $scope.navigateToPatient = function (patientId) {
                        $scope.$modalInstance.dismiss();

                        PatientService.selectedPatientId = patientId;

                        $location.path("/searchpatients");
                    }
                }
            });
        }

        $scope.dc = {};

        $scope.dc.events = [];

        $scope.dc.calendarView = 'month';

        $scope.dc.calendarDay = new Date();

        $scope.dc.eventClicked = function (event) {
            showModal('Clicked', event);
        };

        $scope.dc.eventEdited = function (event) {
            showModal('Edited', event);
        };

        $scope.dc.eventDeleted = function (event) {
            showModal('Deleted', event);
        };

        $scope.dc.setCalendarToToday = function () {
            $scope.dc.calendarDay = new Date();
        };

        $scope.dc.toggle = function ($event, field, event) {
            $event.preventDefault();

            $event.stopPropagation();

            event[field] = !event[field];
        };

        $scope.dc.calendarPrev = function () {
            $scope.calendarControl.prev();
        }

        $scope.dc.calendarNext = function () {
            $scope.calendarControl.next();
        }

        $scope.$watch('dc.calendarDay', function (newValue) {
            getAppointments(newValue.getMonth() + 1, newValue.getFullYear());
        });
    }]);