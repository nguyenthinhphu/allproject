'use strict';
// tạo Serice
UserApp.factory('ListService', ['$http', '$q', function($http, $q){

	return {
		
		// Hàm getAllUsers
		// Lấy toàn bộ thông tin user
			fetchAllUsers: function() {
					return $http.get('http://localhost:8080/listdata')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching users');
										return $q.reject(errResponse);
									}
							);
			},
			// Hàm getUserById
	getUserByID : function(id){
		return $http.get('http://localhost:8080/getid?id='+id).then(
	  function(response){
		  return response.data;
	  },
		function(errResponse){
			console.error('Error while find User by Id.');
			return $q.reject(errResponse);
		}
		);
	},
	// Hàm tạo User
			createuser : function(user) {
				return $http.post('http://localhost:8080/getid', user).then(
						  function(response){
							  return response.data;
						  },
							function(errResponse){
								console.error('Error while find User by Id.');
								return $q.reject(errResponse);
							}
							);
			},
			// Hàm Xóa User
			deleteUserMH02: function(id) {
				return $http.post('http://localhost:8080/get', id).then(
						  function(response){
							  return response.data;
						  },
							function(errResponse){
								console.error('Error while find User by Id.');
								return $q.reject(errResponse);
							}
							);
			},
			// Hàm tìm kiếm User
			// Truyền vào là comId
			findU : function(comId) {
				return $http.post('http://localhost:8080/findCom', comId).then(
						  function(response){
							  return response.data;
						  },
							function(errResponse){
								console.error('Lỗi khi tìm công ty theo id.');
								return $q.reject(errResponse);
							}
						);
			},
			// Hàm getAll Companys
			getAllCompanys : function() {
				return $http.get('http://localhost:8080/listcom').then(
				function(response){
					return response.data;
				},
				function(errResponse){
					console.error('Lỗi khi lấy All Company.');
					return $q.reject(errResponse);
				}			
				);
			},
			// Hàm User Search
			// Truyền vào là 1 user
			getUserSearch : function(user){
				return $http.post('http://localhost:8080/searchuser', user).then(
				function(response){
					return response.data;
				},
				function(errResponse){
					console.error('Lỗi khi search.');
					return $q.reject(errResponse);
				}		
				)
			},
			// Hàm get User By userId
			getUserByUserId : function(userId){
				return $http.post('http://localhost:8080/finduser',userId).then(
			  function(response){
				  return response.data;
			  },
				function(errResponse){
					console.error('Error while find User by Id.');
					return $q.reject(errResponse);
				}
				);
			},
			// Hàm Update User truyền vào là 1 User để update
			updateU : function(UserUpdate) {
				return $http.post('http://localhost:8080/updateUser', UserUpdate).then(
				function(response){
					return response.data;
				},
				function(errResponse){
					console.error('Error while get data update.');
					return $q.reject(errResponse);
				}		
				);
			}
	};
}]);