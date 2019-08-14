app.controller('adminController', ['$scope','ngTableParams','$filter','$window','$http' ,function($scope,ngTableParams,$filter,$window,$http,$rootScope) {
	console.log("Inside admin controller");

	$http.get("/user/getUsers")
    .then(function(response) {
    	console.log(response.data)
    	$scope.users	=	response.data;
    	$scope.usersTable = new ngTableParams({
    	       page: 1,
    	       count: 10
    	   }, {
    	       total: $scope.users.length, 
    	       getData: function ($defer, params) {
    	    	   $scope.data = params.sorting() ? $filter('orderBy')($scope.users, params.orderBy()) : $scope.users;
    	    	   $scope.data = params.filter() ? $filter('filter')($scope.data, params.filter()) : $scope.data;
    	    	   $scope.data = $scope.data.slice((params.page() - 1) * params.count(), params.page() * params.count());
    	    	   $defer.resolve($scope.data);
    	    	}
    	   });
    }, function(response) {
        //Second function handles error
        $scope.content = "Something went wrong";
        console.log("Somenthing went wrong")
      
    });

	
	

	
	
	
 	$scope.changeStatus =	function(id){
		console.log("change status "+id)
 		 $http.get("/user/activateUser?id="+id)
	    .then(function(response) {
	    	console.log(response.data)

	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
	    }); 
		//$window.location.href = 'html/main.htm';
	}
	
	$scope.changeRole =	function(id){
		console.log("change role "+id)
 		 $http.get("/user/changeRole?id="+id)
	    .then(function(response) {
	    	console.log(response.data)

	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
	    }); 
		//$window.location.href = 'html/main.htm';
	} 
	
	
	
	
	
	
	
	
	
	
}]);