'use strict';

AppAdd.controller('AddController', ['$scope', 'AddService', function($scope, AddService) {
	var self = this;
    self.company = [];
	
	
	 
    // find by ocmpany ID
    
    self.getData = function(){
  	  AddService.fetchCompanyId(companyInternalId).then(
  			  function(k){
  				  self.company = k;
  			  }
  	  );
  	  
    };
	
}]);