'use strict';
AppAdd.controller('AddController',['$scope','AddService', function($scope,AddService){
	
	
	 var self = this; // self đại diện cho biến scope
     self.userAdd = null;
     self.listCom = []; // list company
     self.comInfo = null;
     
     self.comNew = {companyName : '', address : '', email: '', telephone: ''};
	 // Get All Company
    self.getAllCompany = function() {
		AddService.getAllCompanys().then(
		function(d){
			self.listCom = d;
		},
		function(errResponse){
			console.error('Cannot get company');
		}		
		);
	};
	self.getAllCompany();
	
	self.comChange = function(){
		var comId = document.getElementById("selectCompanyName").value;
		AddService.findComById(comId).then(
			function(d){
				self.comInfo = d;
			}
		);
	}

	self.executeAdd = function(userAdd){
		AddService.exeAdd(userAdd).then(
				function(){
					console.write('Success');
				}
				)
	}
}]);