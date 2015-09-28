(function() {
	'use strict';       
        
        var Utils = {
            appRedirectTo: function (absoluteAppUrl) {
                window.location = window.location.href.split('#')[0] + '#' + absoluteAppUrl;
            },
            redirectTo: function (absoluteUrl) {
                window.location = window.location.origin + absoluteAppUrl;
            }
	};
        
        var app = angular.module('ClassRecordApp', ['ngRoute']);
    
        app.config( ['$routeProvider', function($routeProvider) {
            $routeProvider
                .when('/add_student', {
                    templateUrl: 'templates/add_student.html',
                    controller: 'AddStudentController'
                })
                .when('/manage_students', {
                    templateUrl: 'templates/manage_students.html',
                    controller: 'ManageStudentsController'
                })
                .when('/class_record_view', {
                    templateUrl: 'templates/class_record_view.html',
                    controller: 'ClassRecordViewController'
                })
                .when('/student_assignments', {
                    templateUrl: 'templates/student_assignments.html',
                    controller: 'StudentAssignmentsController'
                })
                .otherwise({
                    redirectTo: '/'
                });
        }]);
    
        app.controller('DefaultController', ['$scope', function($scope) {
            $scope.navigateTo = function (absoluteAppUrl) {
                Utils.appRedirectTo(absoluteAppUrl);
            };
        }]);
    
        app.controller('AddStudentController', ['$scope', '$http' ,function($scope, $http) {
        	
        }]);
        
        app.controller('AddStudentController', ['$scope', '$http' ,function($scope, $http) {
        	
        }]);
        
        app.controller('AddStudentController', ['$scope', '$http' ,function($scope, $http) {
        	
        }]);
            
})();