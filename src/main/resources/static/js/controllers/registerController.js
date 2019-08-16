app.controller('registerController', function($scope,$window,$http,$rootScope,$mdDialog,$timeout) {
	console.log("Inside register controller");
	$scope.email	=	"";
	$scope.name		=	"";
	$scope.tel	=	"";
	$scope.comment		=	"";
	$scope.pwd	=	"";
	$scope.checkterminos	=	"";
	
	
	$scope.notFilledFields	=	false;
	$scope.emailNotValid	=	false;
	$scope.pwdNotValid	=	false;

	var avoidDouble	=	false;
	
	$scope.register =	function(){
		$timeout(function () {
			avoidDouble	=	false;
	    }, 2000);
		if($scope.pwd.length <8){
			$scope.pwdNotValid	=	true;
				return;
		}
		console.log("Icheckterminos------ " +$scope.checkterminos)
		if(avoidDouble==false){
			avoidDouble=true;
			console.log("email value: "+$scope.email)
			console.log("name value: "+$scope.name)
		if($scope.email == "" || $scope.name == ""|| $scope.tel == ""|| $scope.pwd == ""|| $scope.comment == "" || $scope.checkterminos === false){
			console.log("Inside email vacio")
			$scope.notFilledFields	=	true;
				return;
		}
			//for tests return;
		$scope.notFilledFields	=	false;

		if(!validateEmail($scope.email)){
			$scope.emailNotValid	=	true;
			return;
		}
		$scope.emailNotValid	=	false;
		$http.get("/user/register?username="+$scope.name+"&comment="+$scope.comment+"&mail="+$scope.email+"&tel="+$scope.tel+"&pwd="+$scope.pwd+"")
	    .then(function(response) {
	    	console.log(response.data)
	    	   $mdDialog.show(
					      $mdDialog.alert()
					        .parent(angular.element(document.querySelector('#popupContainer')))
					        .clickOutsideToClose(true)
					        .title('Informacion')
					        .textContent('Tu registro se ha realizado de forma satisfactoria, en breve recibiras un correo con contrasena para poder ingresar al sistema, Gracias.')
					        .ariaLabel('Alert Dialog Demo')
					        .ok('Salir')
					        
					    ).then(function(i){
			    			$window.location.href = '/';
					      });
	    }, function(response) {
	        //Second function handles error
	        console.log("Somenthing went wrong")
	        $mdDialog.show(
				      $mdDialog.alert()
				        .parent(angular.element(document.querySelector('#popupContainer')))
				        .clickOutsideToClose(true)
				        .title('Informacion')
				        .textContent('Por el momento el servicio de registro de cuentas no esta disponible, por favor intenta mas tarde. Gracias.')
				        .ariaLabel('Alert Dialog Demo')
				        .ok('Salir')
				        
				    );
	    });
		}
	
		 
		
	}

	function validateEmail(email) {
		  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		  return re.test(email);
		}
});