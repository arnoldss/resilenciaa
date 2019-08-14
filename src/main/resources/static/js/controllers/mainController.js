app.controller('mainController', ['$scope','$window','$http','ngTableParams','$rootScope','$filter','$timeout' ,function($scope,$window,$http,ngTableParams,$rootScope,$filter,$timeout) {
	console.log("Inside main controller");
	$rootScope.choosed	=	false;
	//$rootScope.idUser	=	"";

	 
 	$scope.isAdmin	=	false;
	$scope.loginError	=	false;
	console.log("EL valor de formSent: "+formSent)
	if(formSent)
		$window.location.href = '#!addDocs';

		 	$scope.changeChoosed =	function(id){
			console.log("change choosed "+id)
			console.log("El objeto: "+JSON.stringify($scope.users))
			
			$("#idusuariov").val(id);
			
			 $http.get("/user/saveUserSelected?idusuario="+id)
			    .then(function(response) {
			    }, function(response) {
			        //Second function handles error
			        $scope.content = "Something went wrong";
			        console.log("Somenthing went wrong")
				});
			
			
			
				for (var key in $scope.users) {
				console.log(key + " -> " + $scope.users[key].id);
				$scope.users[key].choosed =	false;
				$rootScope.choosed	=	false;
			
			}
			for (var key in $scope.users) {
				console.log(key + " -> " + $scope.users[key].id);
				$scope.users[key].choosed =	false;
				$rootScope.choosed	=	false;
				if($scope.users[key].id == id){
					$rootScope.choosed	=	true;
					$rootScope.idUser	=	id;
					$scope.users[key].choosed =	true;
					
				}
			}
	}
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
	$http.get("/user/getUser")
    .then(function(response) {
		$rootScope.user	= response.data;
		if($rootScope.user.role	== 1){
			console.log("Role user is User")
			$rootScope.idUser	= $rootScope.user.id;
			$scope.isAdmin	=	false;
		}
    	if($rootScope.user.role==0)
    	 	$scope.isAdmin	=	true;

    }, function(response) {
        //Second function handles error
        $scope.content = "Something went wrong";
        console.log("Somenthing went wrong")
		$window.location.href = '/';

    });

 
	$scope.logout =	function(){
		//$window.location.href = '#!register';
		console.log("trying to logout ")
 		$http.get("/user/logout")
	    .then(function(response) {
	    	console.log(response.data)
			$window.location.href = '/';
	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
	    });
		//$window.location.href = 'html/main.htm';
	}

}]);