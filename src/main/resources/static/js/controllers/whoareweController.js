app.controller('whoareweController' ,function($scope,ngTableParams,$filter,$window,$http,$rootScope,$mdDialog,$timeout) {
	console.log("Inside admin controller");
	$scope.whoarewe 	=	{"id":"","idUser":"","mision":"","vision":"","valores":"","breveHistoria":"","premiosOrganizacion":""}

		  $http.get("/catalogs/getWhoAreWe?userId="+$rootScope.idUser)
	    .then(function(response) {
	    	console.log(response.data)
	    	console.log("IdUser is: "+$rootScope.idUser)
			$scope.whoarewe	=	response.data;
		    
		}, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		}); 
	$( "#resimg" ).hide();
		 	 $scope.save =	function(id){
		console.log("  save ");
		if(!validateObject()) {
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
	

		$scope.whoarewe.idUser	=			$rootScope.idUser;
		console.log("IdUser is: "+$scope.whoarewe.idUser)
		console.log("IdUser is: "+$rootScope.idUser)

		var parameter = JSON.stringify($scope.whoarewe);
		$http.post("/catalogs/addWhoAreWe", parameter) .then(function(response) {
	    	console.log(response.data)

	    		    	   $mdDialog.show(
            					      $mdDialog.alert()
            					        .parent(angular.element(document.querySelector('#popupContainer')))
            					        .clickOutsideToClose(true)
            					        .title('Informacion')
            					        .textContent('Tu registro se ha realizado de forma satisfactoria, procede al llenado de "Evaluacion"')
            					        .ariaLabel('Alert Dialog Demo')
            					        .ok('Salir')

            					    ).then(function(i){
            			    			$window.location.href = '/main3#!/encuestaAdmin';
            					      });
			
			
	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		});
 	 
	} 

		function validateObject(){
		for (var key in $scope.whoarewe) {
			if ($scope.whoarewe.hasOwnProperty(key)) {
 				console.log(key + " -> " + $scope.whoarewe[key]);
							console.log("key:"+key)
 				if(key =="clasificationId" || key =="propertyTypeId"||key =="id"|| key =="createdAt" || key =="idUser"|| key =="ciudadId"|| key =="estadoId"){
					console.log("key is id:"+key)
					continue;
				}
 				if($scope.whoarewe[key]==""){
					console.log("returning false key:"+key)
					return false
					}
 					return true;
			}
		}
	}

});