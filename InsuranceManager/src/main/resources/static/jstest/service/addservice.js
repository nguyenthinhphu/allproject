'use strict';
AppAdd.factory('AddService', ['$http', '$q', function($http, $q){

	return{
		
		
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
		
		findComById : function(id){
			return $http.post('http://localhost:8080/findCompany', id).then(
					function(response){
						return response.data;
					}
					
			);
		},
		
		exeAdd : function(userAdd){
			return  $http.post('http://localhost:8080/addUserAngular',userAdd).then(
			function(){
			}		
			);
		}
		
	};
	
}]);