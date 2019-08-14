app.controller('sustentabilidadController' ,function($scope,ngTableParams,$filter,$window,$http,$rootScope,$mdDialog,$timeout) {
	console.log("Inside admin controller");
  $scope.fuenteIngresos	=	{};
  $scope.sustentabilidad	=	{"sourceName":"extradata","idUser":"","sourceId":"","benefactor":"","porcentajeAnual":"","comentario":"","status":"1"};
  $http.get("/catalogs/getIncomeSource")
	    .then(function(response) {
	    	console.log(response.data)
			$scope.fuenteIngresos	=	response.data;
	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		}); 
 	$http.get("/catalogs/getSustentabilidad?userId="+$rootScope.idUser)
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
		/* if(!validateObject()){
			console.log("Ingresar data faltante")
			  $mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.querySelector('#popupContainer')))
					.clickOutsideToClose(true)
					.title('Informacion')
					.textContent('Por favor llenar todos los campos, Gracias.')
					.ariaLabel('Alert Dialog Demo')
					.ok('Salir')
									        
									    ).then(function(i){
							    			 
									      }); 
			
		} */
		console.log("QUE HAY: "+JSON.stringify($scope.sustentabilidad.sourceId) )
		console.log("QUE HAY: "+ ($scope.sustentabilidad.sourceId.name) )
		
		$scope.sustentabilidad.sourceName	=	$scope.sustentabilidad.sourceId.name;
		$scope.sustentabilidad.sourceId	=	    $scope.sustentabilidad.sourceId.id;
		console.log("Source Name: "+$scope.sustentabilidad.sourceName);
		$scope.sustentabilidad.idUser	=			$rootScope.idUser;
		console.log("IdUser is: "+$scope.sustentabilidad.idUser)
		console.log("IdUser is: "+$rootScope.idUser)
		console.log("$scope.generalData.fechaConstitucion" )
		var parameter = JSON.stringify($scope.sustentabilidad);
		console.log("Parameter: "+parameter)
		$http.post("/catalogs/addSustentabilidad", parameter) .then(function(response) {
	    	console.log(response.data)
			 		$window.location.href = '#!sustentabilidad';

			
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