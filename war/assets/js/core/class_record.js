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
                //.when('/student_assignments', {
                //    templateUrl: 'templates/student_assignments.html',
                //    controller: 'StudentAssignmentsController'
                //})
                .otherwise({
                    redirectTo: '/'
                });
        }]);
    
        app.controller('DefaultController', ['$scope', function($scope) {
            $scope.navigateTo = function (absoluteAppUrl) {
                Utils.appRedirectTo(absoluteAppUrl);
            };
        }]);
        
///////////////////////////// ADD STUDENT VIEW  //////////////////////////////////////////          
    
        app.controller('AddStudentController', ['$scope', '$http', function($scope, $http) {
        	
        	$scope.getCourses = function () {
        		$http.get('CourseList')
        			.success(function(response){
        				if (response.error == ""){
        					$scope.courses = jQuery.parseJSON(response.courses);
        					$scope.courseSelected = "BSIT";
        				} else {
        					alert(response.error);
        				}
        			}).error(function(response){
        				alert("Unable to get courses because of AJAX error");
        			});
        	};
        	
        	$scope.addCourse = function () {
        		var cls = { key: "", name: "" };
                cls.name = $scope.newCourse;
                $http.post('AddCourse', cls).then(function(response){
                	alert("Added!");
                }, function(response){
                	alert ("Add course ajax error");
                });
        	};
        	
        	$scope.addStudent = function (){ 
        		if (!(typeof $scope.courseSelected === 'undefined')){
        			var first = $('#firstName').val();
        			var last = $('#lastName').val();
        			if (!(/\S/.test(first))){
        				$scope.errorMessage = "Please enter a valid first name. Don't leave it blank";
        			} else if (!(/\S/.test(last))) {
        				$scope.errorMessage =  "Please enter a valid last name. Don't leave it blank";
        			} else {
        				if (confirm('Are you sure you want to add this student?')) {
			        		 var student = { key: "", firstName: "", lastName: "", course: "", birthDate: "" };		        		 
			                 student.firstName = $scope.firstName;
			                 student.lastName = $scope.lastName;
			                 student.course = $scope.courseSelected;
			                 student.birthDate = $scope.birthDate;
			                 $http.post('AddStudent', student)
			                 	.success(function(response){
			                 		if (response.error == ""){
			                 			$scope.successMessage = "Student registered successfully";
			                 			$scope.errorMessage = "";     
			                 			$scope.firstName = "";
			                 			$scope.lastName = "";
			                 			$scope.birthDate = "";
			                 			$scope.courseSelected = "";
			                 		} else {	
			                 			$scope.successMessage = "";
			                 			$scope.errorMessage = response.error;                 			
			                 		}
			                 	}).error(function(response){
			                 		$scope.successMessage = "";
			                 		$scope.errorMessage = "Unable to add student. AJAX error.";
			                 	});
	        			}
        			}
        		} else {
    				alert("Please choose a course.");
    			}
        		
        	};          	
        	
        	$scope.getCourses();        	
        	
        }]);
        
///////////////////////////// MANAGE STUDENT VIEW  //////////////////////////////////////////          
        
        app.controller('ManageStudentsController', ['$scope', '$http' ,function($scope, $http) {
        	$scope.mainStudentList = [];
        	
        	$scope.getStudentsList = function () {
        		$http.get('StudentList').success(function(response){
        			if (response.error == ""){
        				$scope.students = jQuery.parseJSON(response.students);
        				$scope.mainStudentList = jQuery.parseJSON(response.students);
        			} else {
        				alert(response.error);
        			}
        		})
        		.error(function(response){
        			alert("Unable to load classes. AJAX error.");
        		});
        	};
        	
        	$scope.deleteStudent = function (key) {
        		if (confirm('Are you sure you want to unregister this student?')){
        			var student = { key: "", firstName: "", lastName: "", course: "", birthDate: "" };
                    student.key = key;
                    $http.post('DeleteStudent', student).success(function(response){
                		if (response.error == "") {
                			alert("Student was successfully removed.");
                			$scope.getStudentsList();
                		} else {
                			alert(reponse.error);
                		}
                    })
                    .error(function(response){
                    	alert("Unable to delete student. AJAX error.");
                    });                    
        		}
        	};
        	
        	$scope.searchUser = function () {        		
        		$scope.search($scope.searchName, $scope.mainStudentList);        			
        	};
        	
        	$scope.search = function searchStringInArray (name, students) {        		
        		var newStudents = [];        		
			    for (var i = 0; i < students.length; i++) {
			    	if (students[i].firstName.match(new RegExp(name, "i")) || students[i].lastName.match(new RegExp(name, "i"))){
			    		newStudents.push(students[i]);
			    	}
			    }
			    $scope.students = newStudents;
			};
        	
        	$scope.showUpdateModal = function (key) {
        		$scope.key = key;
        		$http.post('GetStudent', {'key':key}).success(function(response){
        			if (response.error == ""){
        				var student = jQuery.parseJSON(response.student);
        				$scope.firstName = student[0].firstName;
        				$scope.lastName = student[0].lastName;
        				$scope.courseSelected = student[0].course;
        				$scope.birthDate = student[0].birthDate;
        			} else {
        				alert (response.error);
        			}
        		}).error(function(response){
        			alert("Unable to fetch student. Ajax error.");
        		});        		
        		$('#update-student-modal').modal('show');        		
        	};
        	
        	$scope.getCourses = function () {
        		$http.get('CourseList')
        			.success(function(response){
        				if (response.error == ""){
        					$scope.courses = jQuery.parseJSON(response.courses);
        				} else {
        					alert(response.error);
        				}
        			}).error(function(response){
        				alert("Unable to get courses because of AJAX error");
        			});
        	};
        	
        	$scope.updateStudent = function () {
        		var first = $('#firstName').val();
    			var last = $('#lastName').val();
    			if (!(/\S/.test(first))){
    				alert("Please enter a valid first name. Don't leave it blank");
    			} else if (!(/\S/.test(last))) {
    				alert("Please enter a valid last name. Don't leave it blank");
    			} else {
	        		if (confirm('Are you sure you want to update this student?')){
	    				var student = { key: "", firstName: "", lastName: "", course: "", birthDate: "" };
		                 student.firstName = $scope.firstName;
		                 student.lastName = $scope.lastName;
		                 student.course = $scope.courseSelected;
		                 student.birthDate = $scope.birthDate;
		                 student.key = $scope.key;
		                 $http.post('UpdateStudent', student)
		                 	.success(function(response){
		                 		if (response.error == ""){		                 			
		                 			alert ("Student updated successfully");	                 			
		                 		} else {	
		                 			alert(response.error);                 			
		                 		}
		                 	}).error(function(response){
		                 		alert("Unable to update student. AJAX error.");
		                 	});
		                 $scope.getStudentsList();
	        		} 
    			}
        	};
        	
        	$scope.getCourses();
        	$scope.getStudentsList();
        }]);
        
///////////////////////////// CLASS RECORD VIEW  //////////////////////////////////////////        
        
        app.controller('ClassRecordViewController', ['$scope', '$http' ,function($scope, $http) {
        	$scope.getClassesList = function () {
        		$http.post('ClassesList', {"classKey": $scope.classSelected}).success(function(response){
        			if (response.error == ""){
        				$scope.classes = jQuery.parseJSON(response.classes);
        			} else {
        				alert(response.error);
        			}
        		})
        		.error(function(response){
        			alert("Unable to load classes. AJAX error.");
        		});
        	};        	
        	
        	$scope.getStudentsList = function () {
        		$http.get('StudentList').success(function(response){
        			if (response.error == ""){
        				$scope.mainStudentList = jQuery.parseJSON(response.students);
        				$scope.mainStudentList[0].firstName;
        			} else {
        				alert(response.error);
        			}
        		})
        		.error(function(response){
        			alert("Unable to load classes. AJAX error.");
        		});
        	};
        	
        	$scope.getStudentsList();
        	
        	$scope.removeStudents = function () {
        		if (confirm('Are you sure you want to remove these students?')){
		    		var records = $('#student-table ').find('input[type="checkbox"]:checked').not('#selectAll').map(function() {		
		    			return $(this).val();
		    		}).get();					    		
		    		if (records.length) {        			
		    			$http.post('RemoveMultipleRecord', {"records": JSON.stringify(records)}).success(function(response){
		    				if (response.error == ""){
		    					$scope.changeClass();
			    				alert("Students successfully removed.");	
		    				} else {
		    					alert (response.error);
		    				}
		    			}).error(function(response){
		    				alert ("Unable to remove records. AJAX error.");
		    			});
		    		} else {
		    			alert ('Please select a student first.');
		    		}
        		}
        	};
        	
        	$scope.getStudentRecord = function (key) {
        		$http.post('GetStudentRecord', {"key":key}).success(function(response){        			
        			if (response.error == ""){
        				var record = jQuery.parseJSON(response.record);
        				$scope.q1 = record[0].quizOne;
        				$scope.q2 = record[0].quizTwo;
        				$scope.q3 = record[0].quizThree;
        				$scope.hw1 = record[0].assignmentOne;
        				$scope.hw2 = record[0].assignmentTwo;
        				$scope.ex1 = record[0].examOne;
        				$scope.ex2 = record[0].examTwo;
        				$scope.recordForUpdate = record[0].key;
        			} else {
        				alert (response.error);
        			}
        		}).error(function(response){
        			alert("Unable to fetch student record. AJAX error.");
        		});
        	};
        	
        	$scope.submitUpdateRecordForm = function () { 
        		if (confirm('Are you sure you want to update this record?')) {
        			var record = {
                            key: "", classKey: "", student: null, quizOne: 0, quizTwo: 0, quizThree: 0,
                            assignmentOne: 0, assignmentTwo: 0, examOne: 0, examTwo: 0
                        };
                        record.key = $scope.recordForUpdate;
                        record.quizOne = $scope.q1;
                        record.quizTwo = $scope.q2;
                        record.quizThree = $scope.q3;
                        record.assignmentOne = $scope.hw1;
                        record.assignmentTwo = $scope.hw2;
                        record.examOne = $scope.ex1;
                        record.examTwo = $scope.ex2;                        
        			$http.post('UpdateStudentRecord', record).success(function(response){
        				if (response.error == ""){
        					alert("Record was succesfully updated.");
        					$scope.changeClass();
        				} else {
        					alert(response.error);
        				}
        			}).error(function(response){
        				alert("Unable to update profile. AJAX error.");
        			});
        		}
        	};
        	
        	$scope.removeRecord = function (key) {
        		if (confirm('Are you sure you want to remove this student from the class?')) {
        			$http.post('RemoveRecord', {"key":key}).success(function(response){
        				if (response.error == ""){
        					$scope.changeClass();
        					alert("Record successfully removed.");
        				} else {
        					alert(response.error);
        				}
        			}).error(function(response){
        				alert ("Unable to remove this record. AJAX error.");
        			});
        		}
        	};
        	
        	
        	
        	$scope.addClass = function () {
        		var newClassName = $('#class_name').val();
        		if (/\S/.test(newClassName)){
        			if (confirm('Are you sure you want to add this class?')){
    	        		$http.post('AddClass', {name: $scope.newClassName}).success(function(response){
    	        			if (response.error == ""){
    	        				$scope.getClassesList();
    	        				alert ("Class successfully added");
    	        			} else {
    	        				alert(response.error);
    	        			}
    	        		}).error(function(response){
    	        			alert ("Unable to add class. AJAX error.");
    	        		});
            		}        			
        		} else {
        			alert ("Please enter a name of a new class.");
        		}
        		
        	};
        	
        	$scope.unassignedList = [];
        	$('#selectAllUnassigned').attr("checked", false);
        	$scope.changeClass = function () {
        		$('#selectAllUnassigned').attr("checked", false);
        		$http.post('GetStudentPerClass', {"classKey":$scope.classSelected}).success(function(response){
        			if (response.error == ""){
        				$scope.records = jQuery.parseJSON(response.records);
        				$scope.mainRecords = jQuery.parseJSON(response.records);  
        				if ($scope.records.length < 15 && $scope.records.length > 0){
        					alert ("Warning. Students enrolled in this class is below the minimum (15).");
        				}
        			} else {
        				alert(response.error);
        			}
        		})
        		.error(function(response){
        			alert("Unable to load classes. AJAX error.");
        		});
        		
        		$http.post('StudentList').success(function(response){
        			if (response.error == ""){
        				$scope.allStudents = jQuery.parseJSON(response.students);
        				$scope.getUnassigned($scope.allStudents, $scope.records);
        			} else {
        				alert(response.error);
        			}
        		})
        		.error(function(response){
        			alert("Unable to load classes. AJAX error.");
        		});
        	};
        	
        	$scope.getUnassigned = function (students, currentRecords) {        		
        		var newList = [];        		
        		
        		for (var i = 0; i < students.length; i++) {
        			var flag = true;
        			for (var j = 0; j < currentRecords.length; j++){        				        		
        				if(students[i].key == currentRecords[j].student.key){
        					flag = false;
        					break;
        				}        				
        			}        		
        			if (flag){
        				newList.push(students[i]);
        			}
        		}
        		$scope.mainUnassignedList = newList;
        		$scope.unassignedList = newList;
        	};
        	
        	$scope.updateClass = function (key) {
        		if (confirm('Are you sure you want to update this class?')){
        			var name = $("input#" + key).val();
        			if (/\S/.test(name)) {
	        			$http.post('UpdateClass', {"name": name, "key": key}).success(function(response){
		        			if (response.error == ""){
		        				$scope.getClassesList();
		        				alert ("Class successfully updated");
		        			} else {
		        				alert(response.error);
		        			}
		        		}).error(function(response){
		        			alert ("Unable to update class. AJAX error.");
		        		});
        			} else {
        				alert ("Please a valid name.");
        			}
        		}
        	};
        	
        	$scope.deleteClass = function (classKey) {
        		if (confirm('Are you sure you want to delete this class?')){
		        	$http.post('DeleteClass', {"key":classKey})
		    		.success(function(response){
		    			if (response.error == ""){
		    				$scope.getClassesList();
		    				$scope.records = [];
		    				alert("Class successfully deleted.");
		    			} else {
		    				alert(response.error);
		    			}
		    		}).error(function(response){
		    			alert("Unable to delete class. AJAX error.");
		    		});
        		}
        	};
        	
        	$scope.selectAll = function () {
        		if ($('input#selectAll').prop('checked')){
        			$('input.record').prop('checked', true);
        		} else {
        			$('input.record').prop('checked', false);
        		}
        	};
        	
        	$scope.selectAllUnassigned = function () {
        		if ($('input#selectAllUnassigned').prop('checked')){
        			$('input.student').prop('checked', true);
        		} else {
        			$('input.student').prop('checked', false);
        		}
        	};
        	
        	$scope.assignStudents = function () {        		
        		if (confirm('Are you sure you want to add these students?')){
		    		var students = $('#unAssignedTable').find('input[type="checkbox"]:checked').not('#selectAllUnassigned').map(function() {		
		    			return $(this).val();
		    		}).get();			
		    		
		    		var classKey = $scope.classSelected;
		    		if (classKey == '' || (typeof classKey === "undefined")){
		    			alert ('Please choose a class first.');
		    		} else if (students.length) {        			
		    			if ($scope.records == 0 && students.length < 15) {
		    				alert ("The minimum number of students per class is 15. Please enroll more.");
		    			} else {
		    				$.post('studentassign', {'students':JSON.stringify(students), 'class': classKey}, function(data, status, jqXHR) {
			    				var names = $.parseJSON(data);
			    				if (names.alreadyAdded.length != 0){				
			    					var msg = "Error! These students were already added: \nYou are not allowed to add these students anymore.\n\n";
			    					for (var i = 0 ;  i < names.alreadyAdded.length; i++){
			    						msg += names.alreadyAdded[i] + "\n";
			    					}
			    					alert(msg);
			    					if (names.newlyAdded.length != 0){
			    						msg = "These students were added:\n\n";
			    						for (var i = 0 ;  i < names.newlyAdded.length; i++){
			    							msg += names.newlyAdded[i] + "\n";
			    						}
			    						alert(msg);
			    					}
			    				} else {
			    					$scope.changeClass();
			    					$scope.searchName = "";
			    					alert ('All students successfully assigned.');
			    				}
			    			});
		    			}
		    		} else {
		    			alert ('Please select a student first.');
		    		}
        		}
        	};
        	
        	$scope.searchUser = function () {        		
        		$scope.search($scope.searchName, $scope.mainUnassignedList);
        	};
        	
        	$scope.searchRecord = function () {
        		var name = $scope.searchRecordName;  
        		var records = $scope.mainRecords; 
        		var newRecords = [];
        		for (var i = 0; i < records.length; i++) {
 			    	if (records[i].student.firstName.match(new RegExp(name, "i")) || records[i].student.lastName.match(new RegExp(name, "i"))){
 			    		newRecords.push(records[i]);
 			    	}
 			    }
 			    $scope.records = newRecords;
        	};
        	
        	$scope.search = function searchStringInArray (name, students) {        		
        		var newStudents = [];        		
			    for (var i = 0; i < students.length; i++) {
			    	if (students[i].firstName.match(new RegExp(name, "i")) || students[i].lastName.match(new RegExp(name, "i"))){
			    		newStudents.push(students[i]);
			    	}
			    }
			    $scope.unassignedList = newStudents;
			};
        	
        	$scope.getClassesList();
        }]);
        
        
///////////////////////////// STUDENT ASSIGNMENT VIEW  //////////////////////////////////////////          
        
        /*
        app.controller('StudentAssignmentsController', ['$scope', '$http' ,function($scope, $http) {
        	
        	$scope.getClassesList = function () {
        		$http.post('ClassesList', {"classKey": $scope.classSelected}).success(function(response){
        			if (response.error == ""){
        				$scope.classes = jQuery.parseJSON(response.classes);
        			} else {
        				alert(response.error);
        			}
        		})
        		.error(function(response){
        			alert("Unable to load classes. AJAX error.");
        		});
        	};
        	
        	$scope.updateClass = function (key) {
        		if (confirm('Are you sure you want to update this class?')){
        			var name = $("input#" + key).val();
        			$http.post('UpdateClass', {"name": name, "key": key}).success(function(response){
	        			if (response.error == ""){
	        				$scope.getClassesList();
	        				alert ("Class successfully updated");
	        			} else {
	        				alert(response.error);
	        			}
	        		}).error(function(response){
	        			alert ("Unable to update class. AJAX error.");
	        		});
        		}
        	};
        	
        	$scope.addClass = function () {
        		if (confirm('Are you sure you want to add this class?')){
	        		$http.post('AddClass', {name: $scope.newClassName}).success(function(response){
	        			if (response.error == ""){
	        				$scope.getClassesList();
	        				alert ("Class successfully added");
	        			} else {
	        				alert(response.error);
	        			}
	        		}).error(function(response){
	        			alert ("Unable to add user. AJAX error.");
	        		});
        		}
        	};
        	        	
        	
        	$scope.assignStudents = function () {
        		if (confirm('Are you sure you want to add these students?')){
		    		var students = $('#resultTable').find('input[type="checkbox"]:checked').not('#selectAll').map(function() {		
		    			return $(this).val();
		    		}).get();			
		    		
		    		var classKey = $scope.classSelected;
		    		if (classKey == '' || (typeof classKey === "undefined")){
		    			alert ('Please choose a class first.');
		    		} else if (students.length) {        			
		    			$.post('studentassign', {'students':JSON.stringify(students), 'class': classKey}, function(data, status, jqXHR) {
		    				var names = $.parseJSON(data);
		    				if (names.alreadyAdded.length != 0){				
		    					var msg = "Error! These students were already added: \nYou are not allowed to add these students anymore.\n\n";
		    					for (var i = 0 ;  i < names.alreadyAdded.length; i++){
		    						msg += names.alreadyAdded[i] + "\n";
		    					}
		    					alert(msg);
		    					if (names.newlyAdded.length != 0){
		    						msg = "These students were added:\n\n";
		    						for (var i = 0 ;  i < names.newlyAdded.length; i++){
		    							msg += names.newlyAdded[i] + "\n";
		    						}
		    						alert(msg);
		    					}
		    				} else {
		    					alert ('All students successfully assigned.');
		    				}
		    			});
		    			$http.post('GetStudentKeysFromClass', {"classKey":$scope.classSelected}).success(function(response){
		    				if (response.error == ""){
		    					 
		    				} else {
		    					alert (response.error);
		    				}
		    			}).error(function(response){
		    				alert ("Unable to fetch student keys. Ajax Error.");
		    			});
		    		} else {
		    			alert ('Please select a student first.');
		    		}
        		}
        	};
        	
        	$scope.getStudentsList = function () {
        		$http.get('StudentList').success(function(response){
        			if (response.error == ""){
        				$scope.students = jQuery.parseJSON(response.students);
        			} else {
        				alert(response.error);
        			}
        		})
        		.error(function(response){
        			alert("Unable to load classes. AJAX error.");
        		});
        	};
        	
        	$scope.selectAll = function () {
        		if ($('input#selectAll').prop('checked')){
        			$('input.student').prop('checked', true);
        		} else {
        			$('input.student').prop('checked', false);
        		}
        	};
        	
        	$scope.changeClass = function () {
        		$http.post('GetStudentKeysFromClass', {"classKey":$scope.classSelected}).success(function(response){
        			if (response.error == ""){
        				$scope.studentList = jQuery.parseJSON(response.keys);
        			} else {
        				alert(response.error);
        			}
        		})
        		.error(function(response){
        			alert("Unable to load classes. AJAX error.");
        		});
        	};
        	
        	$scope.searchUser = function () {
        		if ($scope.searchName == ""){
        			$scope.getStudentsList();
        		} else {
	        		var student = { key: "", firstName: "", lastName: "", course: "", birthDate: "" };
	                student.firstName = $scope.searchName;
	                student.lastName = $scope.searchName;
	                $http.post('SearchStudent', student).success(function(response){
	            		if (response.error == "") {
	            			$scope.students = jQuery.parseJSON(response.students);
	            		} else {
	            			alert(reponse.error);
	            		}
	                })
	                .error(function(response){
	                	alert("Unable to delete student. AJAX error.");
	                });	        		
        		}
        	};
        	
        	$scope.checkStudent = function (classKey) {
        		if ($scope.studentList.indexOf(classKey) == -1) {
        			return false;
        		} else {
        			return true;
        		}
        	};
        	
        	$scope.deleteClass = function (classKey) {
        		if (confirm('Are you sure you want to delete this class?')){
		        	$http.post('DeleteClass', {"key":classKey})
		    		.success(function(response){
		    			if (response.error == ""){
		    				$scope.getClassesList();
		    				alert("Class successfully deleted.");
		    			} else {
		    				alert(response.error);
		    			}
		    		}).error(function(response){
		    			alert("Unable to delete class. AJAX error.");
		    		});
        		}
        	};
        	
        	$scope.getStudentsList();        	
        	$scope.getClassesList();
        	
        }]);
        */        
            
})();
