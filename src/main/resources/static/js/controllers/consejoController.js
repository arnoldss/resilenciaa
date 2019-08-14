app.controller('consejoController' ,function($scope,ngTableParams,$filter,$window,$http,$rootScope,$mdDialog,$timeout) {
	console.log("Inside consejoController  ");
	$scope.positions	=	{};
  $scope.consejo	=	{"positionName":"extradata","apellido":"","fechaFinal":"","fechaInicio":"","nombre":"","comentario":"","idUser":""};
  $http.get("/catalogs/getPositions")
	    .then(function(response) {
	    	console.log(response.data)
			$scope.positions	=	response.data;
	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		}); 
 	$http.get("/catalogs/getConsejo?userId="+$rootScope.idUser)
    .then(function(response) {
    	console.log(response.data)
    	$scope.users	=	response.data;
    	$scope.usersTable = new ngTableParams({
    	       page: 1,
    	       count: 100
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
	$scope.remove = function(id){
		console.log("EL id es: "+id)
		  $http.get("/catalogs/removeSustentabilidad?id="+id)
	    .then(function(response) {
			$window.location.href = '#!sustentabilidad';

		}, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		}); 
	}
 	 $scope.save =	function(id){
		console.log("  save ");
		$scope.consejo.positionName	=	$scope.consejo.positionName.name;
		$scope.consejo.idUser	=			$rootScope.idUser;
		console.log("IdUser is: "+$scope.consejo.idUser)
		console.log("IdUser is: "+$rootScope.idUser)
		console.log("$scope.consejo" )
		var parameter = JSON.stringify($scope.consejo);
		console.log("Parameter: "+parameter)
		$http.post("/catalogs/addConsejo", parameter) .then(function(response) {
	    	console.log(response.data)
			 		$window.location.href = '#!consejo';

			
	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		});  
  
	} 
	function validateObject(){
		for (var key in $scope.addSustentabilidad) {
			if ($scope.addSustentabilidad.hasOwnProperty(key)) {
 				console.log(key + " -> " + $scope.addSustentabilidad[key]);
							console.log("key:"+key)
 				if(key =="clasificationId" || key =="propertyTypeId"||key =="id"|| key =="createdAt" || key =="idUser"|| key =="ciudadId"|| key =="estadoId"){
					console.log("key is id:"+key)
					continue;
				}
 				if($scope.addSustentabilidad[key]==""){
					console.log("returning false key:"+key)
					return false
					}
 					return true;
			}
		}
	}
});