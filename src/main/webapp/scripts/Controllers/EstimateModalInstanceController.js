patientBillingApp.controller('EstimateModalInstanceController', ['$scope', '$rootScope', '$modalInstance', 'estimates', 'patientName', 'PatientService',
    function ($scope, $rootScope, $modalInstance, estimates, patientName, PatientService) {
        var serviceTax;

        $scope.emic = {};

        $scope.emic.estimates = new kendo.data.ObservableArray(JSON.parse(JSON.stringify(estimates)));

        $scope.emic.patientName = patientName;

        $scope.emic.totalEstimatedBill = kendo.toString(kendo.parseFloat($rootScope.sum($scope.emic.estimates, 'totalCharge')), 'c');

        PatientService.getAllServices().success(function (data) {
            $scope.emic.servicesMasterData = new kendo.data.DataSource({
                data: data
            });

            data.forEach(function (masterData) {
                $scope.emic.estimates.forEach(function (estimate) {
                    if (estimate.chargeTypeId == masterData.chargeTypeId) {
                        estimate.set("defaultCharge", masterData.defaultCharge);
                    }
                })
            });
        });

        PatientService.getTaxRates().success(function (data) {
            data.forEach(function (tax) {
                if (tax.taxItem == 'ServiceTax') {
                    serviceTax = tax.taxRate;
                }
            });
        });

        $scope.emic.estimategridoptions = {
            columns: [
                { title: ' ', field: "isSelected", template: "<input name='isSelected' ng-click='emic.checkboxClicked($event)' type='checkbox' data-bind='checked: isSelected' #= isSelected ? checked='checked' : '' #/>", width: "20px" },
                { field: "typeOfService", width: "100px", editor: servicesDropDownEditor, headerTemplate: '<button class="btn btn-primary" style="float: right" ng-click="emic.addNewEstimate()"><span class="glyphicon glyphicon-plus"></span></button> Service' },
                { field: "dateOfService", title: "Date", width: "50px", template: "#= kendo.toString(kendo.parseDate(dateOfService, 'MM-dd-yyyy'), 'MM-dd-yyyy') #", editor: datepicker, format: "{0:MM-dd-yyyy}" },
                { field: "numberOfSessions", title: "Sessions", width: "50px" },
                { field: "defaultCharge", title: "Default", width: "50px", template: '#= kendo.toString(kendo.parseFloat(defaultCharge), "c") #', editable: false },
                { field: "baseCharge", title: "Charge", editor: editNumberWithoutSpinners, width: "50px", template: '#= kendo.toString(kendo.parseFloat(baseCharge), "c") #' },
                { field: "discount", title: "% Discount", editor: editNumberWithoutSpinners, width: "50px" },
                { field: "tax", title: "Tax", editor: editNumberWithoutSpinners, width: "50px", template: '#= kendo.toString(kendo.parseFloat(tax), "c") #' },
                { field: "totalCharge", title: "Total", editor: editNumberWithoutSpinners, width: "50px", template: '#= kendo.toString(kendo.parseFloat(totalCharge), "c") #' },
                { field: "comments", title: "Comment", width: "150px" }
            ],
            selectable: 'row',
            navigatable: true,
            sortable: true,
            height: 250,
            editable: true,
            scrollable: true,
            edit: function (e) {
                var columnIndex = this.cellIndex(e.container);

                var fieldName = this.thead.find("th").eq(columnIndex).data("field");

                if (fieldName == 'defaultCharge')
                    this.closeCell();

                var input = e.container.find("input");

                input.select();
            }
        }

        $scope.emic.activate = function () {
            var output = {};
            output.estimates = [];
            output.action = 'ActivateEstimates';

            $scope.emic.estimates.forEach(function (estimate) {                
                if (estimate.isSelected) {
                    output.estimates.push(estimate);
                }
            });

            $modalInstance.close(output);
        }

        $scope.emic.ok = function () {
            var output = {};
            output.estimates = $scope.emic.estimates;
            output.action = 'SaveEstimates';

            $modalInstance.close(output);
        };

        $scope.emic.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.emic.addNewEstimate = function (event) {
            var estimate = {
                isSelected: false,
                patientEstimatesId: 0,
                chargeTypeId: 0,
                typeOfService: '',
                dateOfService: new Date(),
                numberOfSessions: 1,
                baseCharge: 0,
                tax: 0,
                totalCharge: 0,
                defaultCharge: 0,
                comments: '',
                discount: 0
            };

            $scope.emic.estimates.unshift(estimate);
        }

        function servicesDropDownEditor(container, options) {
            $('<input required data-text-field="chargeName" data-value-field="chargeName" data-bind="value:typeOfService"/>')
                .appendTo(container)
                .kendoComboBox({
                    autoBind: false,
                    dataSource: $scope.emic.servicesMasterData,
                    select: function (e) {
                        var dataItem = this.dataItem(e.item.index());

                        var selectedRecord = $scope.emic.estimatedatagrid.dataItem($scope.emic.estimatedatagrid.select());

                        selectedRecord.set("defaultCharge", dataItem.defaultCharge);

                        selectedRecord.set("chargeTypeId", dataItem.chargeTypeId);
                    }
                });
        }

        function datepicker(container, options) {
            $('<input data-text-field="' + options.field + '" data-value-field="' + options.field + '" data-bind="value:' + options.field + '" data-format="' + options.format + '"/>')
            .appendTo(container)
            .kendoDatePicker({});
        }

        function editNumberWithoutSpinners(container, options) {
            $('<input data-text-field="' + options.field + '" ' +
                    'data-value-field="' + options.field + '" ' +
                    'data-bind="value:' + options.field + '" ' +
                    'data-format="' + options.format + '"/>')
                    .appendTo(container)
                    .kendoNumericTextBox({
                        spinners: false
                    });
        }

        $scope.emic.estimates.bind("change", function (e) {
            if (e.field == 'baseCharge') {
                e.items[0].set('tax', kendo.parseFloat(e.items[0].get('baseCharge')) * serviceTax / 100);

                e.items[0].set('totalCharge', kendo.parseFloat(e.items[0].get('baseCharge')) + kendo.parseFloat(e.items[0].get('tax')));
            }

            if (e.field == 'discount') {
                var discount = kendo.parseFloat(e.items[0].get('baseCharge')) * kendo.parseFloat(e.items[0].get('discount')) / 100;

                e.items[0].set('tax', kendo.parseFloat(e.items[0].get('baseCharge') - discount) * serviceTax / 100);

                e.items[0].set('totalCharge', kendo.parseFloat(e.items[0].get('baseCharge') - discount + kendo.parseFloat(e.items[0].get('tax'))));
            }
        });

        $scope.emic.checkboxClicked = function (e) {
            var element = $(e.currentTarget);

            var checked = element.is(':checked')

            row = element.closest("tr"),

          	grid = $scope.emic.estimatedatagrid,

          	dataItem = grid.dataItem(row);

            dataItem.isSelected = checked;

            if (checked) {
                row.addClass("k-state-selected");
            } else {
                row.removeClass("k-state-selected");
            }
        }
    }]);