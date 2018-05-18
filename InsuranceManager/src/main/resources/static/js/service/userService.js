'use strict';

// Tạo Service
App.factory('UserService', ['$http', '$q', function($http, $q){

	// Return 
	return {
		// Hàm getAllUser
			fetchAllUsers: function() {
					return $http.post('http://localhost:8080/list') // lấy dữ liệu từ url
							.then(
									function(response){
										return response.data; // trả lại dữ liệu nếu lấy ok
									}, 
									function(errResponse){ // trả lại lỗi nếu lấy ko ok
										console.error('Error while fetching users');
										return $q.reject(errResponse);
									}
							);
			},
			fetchAllCompany : function(){ // Hàm lấy toàn bộ AllCompany
				// lấy từ 1 url
				return $http.post('http://localhost:8080/list').then(
				function(response){
					return response.data.listCompany; // trả lại dữ liệu lấy được
				}		
				);
			},
			//Hàm getDate Từ url
			getData : function(){
				return $http.get('http://localhost:8080/getdata').then(
				function(response){
					return response.data;
				}		
				);
			},
			// search companyById
			fetchCompanyById : function(companyInternalId){
				return $http.get('http://localhost:8080/getDataAdd', companyInternalId).then(
				function(response){
					return response.data;// trả lại dữ liệu lấy được
				}		
				);
			},
			// Hàm Search All Users
		    searchAllUser : function(tblUser){ // search user
		    	return $http.post('http://localhost:8080/listall', tblUser) // lấy từ 1 url
				.then(
						function(response){
							return response.data; // trả lại dũ liệu
						}, 
						function(errResponse){ // Thông báo khi lỗi
							console.error('Error while creating user');
							return $q.reject(errResponse);
						}
				);
		    },
	};

}]);
