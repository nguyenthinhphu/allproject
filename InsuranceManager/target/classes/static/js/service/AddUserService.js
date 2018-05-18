'use strict';

AppAdd.factory('AddService', ['$http', '$q', function($http, $q){
	return{
		// search companyById
		
		fetchCompanyById : function(id){
			return $http.get('http://localhost:8080/getDataAdd', id).then(
			function(response){
				return response.data;
			}		
			);
		}	
	}
}]);