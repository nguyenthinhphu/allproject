'use strict';
// Tao App moi angular JS
App
		.controller(
				'UserController',
				[
						'$scope',
						'UserService',
						function($scope, UserService) {
							var self = this; // tao bien self laf $scope
							self.listView = []; // tao du lieu listview angular js
							self.company = []; // tao list company
							self.tblUser = {userFullname:'', tblCompany : {companyName:''}, tblInsurance :{insuranceNumber:'', placeOfRegister:''}};
							$scope.rowsPerPage = 5; // scope page
							// Hàm lấy toàn bộ thông tin Users
							self.fetchAllUsers = function() {
								// Kiem tra sesion du lieu search
								var searchs = sessionStorage
								.getItem("tblUserSession");
								if(searchs != null){
								UserService
										.fetchAllUsers() // goi hàm get All User từ Service
										.then(
												// trả lại dữ liệu get All
												function(d) {
													var searchs = sessionStorage
															.getItem("tblUserSession");
													if (searchs != null) {
														self.tblUser = JSON
																.parse(sessionStorage
																		.getItem("tblUserSession"));
													} else {
													}
													self.listView = d;
												},
												function(errResponse) {
													console
															.error('Error while fetching Currencies');
												});
								}
								else{
									UserService.getData().then(
									function(s){
										self.listView = s;
										self.tblUser.tblCompany.companyName = self.listView.listCompany[0].companyName;
									}		
									);
								}
							};

							self.fetchAllCompany = function() {
								UserService.fetchAllCompany().then(function(s) {
									self.companys = s;
								});
							};

							// Call Funcction getAllUser
							self.fetchAllUsers();

							// search user
							self.searchAllUser = function(tblUser) {
								UserService
										.searchAllUser(tblUser)
										.then(
												self.fetchAllUsers,
												function(errResponse) {
													console
															.error('Error while searching User.');
												});
							};
							// Tìm kiếm Company theo Id
							self.fetchCompanyById = function() {
								UserService.fetchCompanyId(companyInternalId)
										.then(function(k) {
											self.company = k;
										});
							};
							// Hàm khi nhấn nút Submit tìm kiếm user
							self.submit = function() {
								$scope.paginator.page = 0;
//								var comId = document.getElementById("selectCompanyName").value;
//								self.tblUser.tblCompany.companyName = comId;
								// thực hiện search User theo tblUser truyền vào
								self.searchAllUser(self.tblUser);
								sessionStorage.setItem("tblUserSession", JSON
										.stringify(self.tblUser)); // lưu trữ tblUser Search vào session Storage
								var search = JSON.parse(sessionStorage
										.getItem("tblUserSession"));

							};
						} ]);
