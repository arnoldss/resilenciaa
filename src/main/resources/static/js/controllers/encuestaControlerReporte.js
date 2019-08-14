app.controller('encuestaControlerReporte' ,function($scope,ngTableParams,$filter,$window,$http,$rootScope,$mdDialog,$timeout,$routeParams
		
) {
	console.log("Inside encuestaController  ");
  //$scope.encuesta	=	{};
  //$scope.subareas	=	{};
  //$scope.preguntas	=	{};

  //$scope.questionchoosed	=	false;
  //$scope.responsechoosed	=	false;

	$scope.generalData	=	{ "id":"0", "createdAt":"","idUser":"","proyectoReciente":"","nombre":"","razonSocial":"","rfc":"","clasificationId":"","fechaConstitucion":"",
			"inicioOperacion":"","propertyTypeId":"","comentarios":"","calle":"","numero":"","colonia":"","codigoPostal":"","clasificationName":"","stateName":"Nuevo Leon","propertyName":""
									,"ciudadId":"","estadoId":"","pais":"","telefonoOficina":"","www":"","email":"","nombreDelContacto":"","telefonoDeContacto":"","emailDeContacto":"",
								"propertyChoosen":"object","city":"object","clasificationChoosen":"object","areachoosed":"object","subareachoosed":"object","preguntachoosed":"object"};
  //$scope.catalogochoosed ="";
  //$scope.namecatalog	=	"";
  //$scope.ppregunta	=	"";
  //$scope.spregunta	=	"";
  //$scope.tpregunta	=	"";
  
  //$scope.subareachoosed	=	false;
  //$scope.areachoosed	=	{};
  $scope.sustentabilidad	=	{"sourceName":"extradata","idUser":"","sourceId":"","benefactor":"","porcentajeAnual":"","comentario":"","status":"1"};
  $( "#resimg" ).hide();
  
$http.get("/contestaEncuesta/getUsersEncuesta")
  .then(function(response) {
  	console.log(response.data)
  	$scope.users	=	response.data;
  	$scope.usersEncTable = new ngTableParams({
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


  
  
  $http.get("/contestaEncuesta/reporteWebEncuesta?idusuario="+$routeParams.idusuario)
  .then(function(response) {
  	console.log(response.data)
		$scope.encuesta	=	response.data;
  	console.log("Resultado encuesta")
  	console.log(JSON.stringify($scope.encuesta))
  }, function(response) {
      //Second function handles error
      $scope.content = "Something went wrong";
      console.log("Somenthing went wrong")
	}); 
  
  
 /* $http.get("/contestaEncuesta/generaExcel")
  .then(function(response) {
  	console.log(response.data)

  }, function(response) {
      //Second function handles error
      $scope.content = "Something went wrong";
      console.log("Somenthing went wrong")
  });*/
   

  
  

	

	
  
  /*
  $scope.deleteEncuesta =	function(idusuario){
		console.log("Eliminando encuesta usuario  "+idusuario)
		
	    contestaEncuesta/deleteEncuestaUser?idusuario={{user.id}}
		 $http.get("/user/changeRole?id="+id)
	    .then(function(response) {
	    	console.log(response.data)

	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
	    }); 
	} 
  */

  
 
  
	
 
});




