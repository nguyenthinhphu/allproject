'use strict';

UserApp.controller('ListController', ['$scope', 'ListService', function($scope, ListService) {
          var self = this; // self đại diện cho biến scope
          self.users=[]; // đối tượng khai báo list All
          // KHai báo đối tượng Search trong đó có cả tblInsurance : insurance Number, placeOfRegister
          self.usersearch = {userFullname:'', tblInsurance:{placeOfRegister: '', insuranceNumber:''}}
          self.listCom = []; // list company
          self.userAdd = {userFullname:'', userSexDivision:'', birthdate: null};
          
          //pagging
          
          $scope.rowsPerPage = 5;
          // tao user de update
          
          self.UserUpdate = {userInternalId: null, userFullname: '', userSexDivision: '', birthdate:null, tblInsurance :{insuranceNumber: '', insuranceStartDate: null, insuranceEndDate: null, placeOfRegister: ''}}
          // Tạo hàm getAllUser
          self.fetchAllUsers = function(){
        	  ListService.fetchAllUsers()
                  .then(
      					       function(d) {
      						        self.users = d;
      					       },
            					function(errResponse){
            						console.error(' Lỗi Fetch Users');
            					}
      			       );
          };
          // Lay toan bo users
	self.fetchAllUsers();
	
	// Ham de tao user
     self.createUser = function(user){
    	 ListService.createuser(user).then(
    	self.fetchAllUsers,	
    	function(errResponse){
			console.error(' Lỗi get All Users');
		}
    	 );
     };
     // Get All Company
     self.getAllCompany = function() {
		ListService.getAllCompanys().then(
		function(d){
			self.listCom = d;
		},
		function(errResponse){
			console.error('Cannot get company');
		}		
		);
	};
	// Sửa thông tin user
	
	self.getUserUpdate = function(id){
		ListService.getUserByUserId(id).then(
			function(d){
				self.UserUpdate.userId = self.users.userInternalId;
				self.UserUpdate = d;
			
		},
		function(errResponse){
			console.error(' Lỗi get Users');
		}
			);
	};
	
	self.updateUs = function(UserUpdate){
		ListService.updateU(UserUpdate).then(
				self.fetchAllUsers,
		function(errResponse){
			console.error(' Lỗi update User');
		}
			);
	};
	// Lay toan bo Company
	self.getAllCompany();
	
	// Khi click Submit
     self.submit = function(){
   	  self.getUserById(self.usersearch);
     };
     // Search user
     self.getUserById = function(usersearch){
    	 ListService.getUserSearch(usersearch).then(
    	function(d){
    		self.users = d;
    	},
    	function(errResponse){
			console.error('Lỗi khi tìm Users');
		}
    	 );
     };
     
     // Submit khi Add User
     self.myFunc = function() {
    	 self.createUser(self.userAdd);
	};
	
	// xoa user Khi click link Delete
	self.deleteU = function(id) {
		self.deleteUser(id); // goi ham deleteUser
	};
	// ham xoa user Tạo ham xoa User
	self.deleteUser = function(id){
		ListService.deleteUserMH02(id).then(
		self.fetchAllUsers,	
		function(errResponse){
			console.error('Lỗi khi xóa Users!');
		}
		);
	};
	
	// Ham get Value Company Id
	self.GetValue = function(){
		var comId = document.getElementById("companyId").value;
		self.findUserByComId(comId); // goi ham find User
	};
	// Hàm tim kiếm user By Com Id
	self.findUserByComId = function(comId) {
		ListService.findU(comId).then(
				function(d){
					self.users = d;
				},
    	function(errResponse){
			console.error('Lỗi khi tìm User bằng Com Id');
		}	
		);
	};
}]);