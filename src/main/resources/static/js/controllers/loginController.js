app.controller('loginController', function($scope,$window,$http,$rootScope,$base64) {
	console.log("Inside login controller");
	$scope.username	=	"";
	$scope.password	=	"";
	$rootScope.user	=	{
			allowed:false,
			objectUser:""
	}
	$rootScope.idUser	=	"";
	$scope.loginError	=	false;
	$rootScope.showResult	=	false;
	$scope.login =	function(){
		//$window.location.href = '#!register';

		console.log("username: "+$scope.username)
		console.log("password: "+$scope.password)
		$http.get("/user/login?username="+$scope.username+"&pwd="+$scope.password)
	    .then(function(response) {
	    	console.log(response.data)
	    	if(response.data.allowed === true){
	    		console.log("allow entrance to application")
	    		$rootScope.user.allowed		=	true;
				$rootScope.user.objectUser	=	response.data.user;
				
				 

	    		var us = response.data.user.email;
	    		var bs = response.data.user.pwd;
	    		var dec = $base64.encode($scope.username+"_"+$scope.password)
	    			$window.location.href = '/main3';
	    		 

	    	}
	    	else{
	    		$scope.loginError	=	true;

	    	}
	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
	    });
		//$window.location.href = 'html/main.htm';
	}

});