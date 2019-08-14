app.controller('generalDataController' ,function($scope,ngTableParams,$filter,$window,$http,$rootScope,$mdDialog,$timeout) {
	console.log("Inside admin controller");
	$scope.generalData	=	{ "id":"0", "createdAt":"","idUser":"","proyectoReciente":"","nombre":"","razonSocial":"","rfc":"","clasificationId":"","fechaConstitucion":"",
	"inicioOperacion":"","propertyTypeId":"","comentarios":"","calle":"","numero":"","colonia":"","codigoPostal":"","clasificationName":"","stateName":"Nuevo Leon","propertyName":""
							,"ciudadId":"","estadoId":"","pais":"","telefonoOficina":"","www":"","email":"","nombreDelContacto":"","telefonoDeContacto":"","emailDeContacto":"",
						"propertyChoosen":"object","city":"object","clasificationChoosen":"object"};
  	 //$scope.selectedOption =  {"id":0,"name":"Propia","status":1,"createdAt":1526792400000};
	 $scope.clasifications =	{};
	 $scope.properties	=	{};
	 $scope.states	=	{};
	 $scope.cities	=	{};
	 $( "#resimg" ).remove();
	  $http.get("/catalogs/getGeneralData?userId="+$rootScope.idUser)
	    .then(function(response) {
	    	console.log(response.data)
			$scope.generalData	=	response.data;
			$scope.generalData.fechaConstitucion	=	new Date($scope.generalData.fechaConstitucion);
			$scope.generalData.inicioOperacion	=	new Date($scope.generalData.inicioOperacion);

		}, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		}); 
  $http.get("/catalogs/getClasification")
	    .then(function(response) {
	    	console.log(response.data)
			$scope.clasifications	=	response.data;
	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		}); 
		  $http.get("/catalogs/getProperties")
	    .then(function(response) {
	    	console.log(response.data)
			$scope.properties	=	response.data;
			console.log("Properties: "+JSON.stringify($scope.properties))
	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		});  
		  $http.get("/catalogs/getStates")
	    .then(function(response) {
	    	console.log(response.data)
			$scope.states	=	response.data;
	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		});  
		  $http.get("/catalogs/getCities")
	    .then(function(response) {
	    	console.log(response.data)
			$scope.cities	=	response.data;
	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		});  
 	 $scope.save =	function(id){
		console.log("  save "+$scope.generalData.proyectoReciente);
		if(!validateObject()){
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
			return;
		}
		$scope.generalData.propertyTypeId	=	$scope.generalData.propertyChoosen.id;
		$scope.generalData.clasificationId	=	$scope.generalData.clasificationChoosen.id;
		$scope.generalData.ciudadId	=	$scope.generalData.city.id;

		$scope.generalData.idUser	=			$rootScope.idUser;
		console.log("IdUser is: "+$scope.generalData.idUser)
		console.log("IdUser is: "+$rootScope.idUser)
		console.log("$scope.generalData.fechaConstitucion" +$scope.generalData.fechaConstitucion)
		var parameter = JSON.stringify($scope.generalData);
		$http.post("/catalogs/addGeneralData", parameter) .then(function(response) {
	    	console.log(response.data)
	    	   $mdDialog.show(
					      $mdDialog.alert()
					        .parent(angular.element(document.querySelector('#popupContainer')))
					        .clickOutsideToClose(true)
					        .title('Informacion')
					        .textContent('Tu registro se ha realizado de forma satisfactoria.')
					        .ariaLabel('Alert Dialog Demo')
					        .ok('Salir')
					        
					    ).then(function(i){
			    			//$window.location.href = '/';
					      });
			
	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		});  
 		/* $http.get("/user/activateUser?id="+id)
	    .then(function(response) {
	    	console.log(response.data)

	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		}); 
		*/
		//$window.location.href = 'html/main.htm';
	} 
	function validateObject(){
		for (var key in $scope.generalData) {
			if ($scope.generalData.hasOwnProperty(key)) {
 				console.log(key + " -> " + $scope.generalData[key]);
							console.log("key:"+key)
 				if(key =="clasificationId" || key =="propertyTypeId"||key =="id"|| key =="createdAt" || key =="idUser"|| key =="ciudadId"|| key =="estadoId"){
					console.log("key is id:"+key)
					continue;
				}
 				if($scope.generalData[key]==""){
					console.log("returning false key:"+key)
					return false
					}
 					return true;
			}
		}
	}
 
});