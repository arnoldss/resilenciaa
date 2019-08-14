app.controller('catalogoadminController' ,function($scope,ngTableParams,$filter,$window,$http,$rootScope,$mdDialog,$timeout) {
	console.log("Inside catalogoadminController  ");
  $scope.areas	=	{};
  $scope.subareas	=	{};
  $scope.preguntas	=	{};

  $scope.questionchoosed	=	false;
  $scope.responsechoosed	=	false;

	$scope.generalData	=	{ "id":"0", "createdAt":"","idUser":"","proyectoReciente":"","nombre":"","razonSocial":"","rfc":"","clasificationId":"","fechaConstitucion":"",
			"inicioOperacion":"","propertyTypeId":"","comentarios":"","calle":"","numero":"","colonia":"","codigoPostal":"","clasificationName":"","stateName":"Nuevo Leon","propertyName":""
									,"ciudadId":"","estadoId":"","pais":"","telefonoOficina":"","www":"","email":"","nombreDelContacto":"","telefonoDeContacto":"","emailDeContacto":"",
								"propertyChoosen":"object","city":"object","clasificationChoosen":"object","areachoosed":"object","subareachoosed":"object","preguntachoosed":"object"};
  $scope.catalogochoosed ="";
  $scope.namecatalog	=	"";
  $scope.ppregunta	=	"";
  $scope.spregunta	=	"";
  $scope.tpregunta	=	"";
  
  $scope.subareachoosed	=	false;
  $scope.areachoosed	=	{};
  $scope.sustentabilidad	=	{"sourceName":"extradata","idUser":"","sourceId":"","benefactor":"","porcentajeAnual":"","comentario":"","status":"1"};
  $http.get("/catalogs/getArea")
  .then(function(response) {
  	console.log(response.data)
		$scope.areas	=	response.data;
  }, function(response) {
      //Second function handles error
      $scope.content = "Something went wrong";
      console.log("Somenthing went wrong")
	}); 
  
  $scope.loadcatalogSubArea = function(id){
		$http.get("/catalogs/getCatalog?catalog="+$scope.catalogochoosed+"&areaId="+$scope.generalData.areachoosed.id)
	    .then(function(response) {
	    	console.log(response.data)
	    	$scope.catalogs	=	response.data;
	    	$scope.subareas	=	response.data;
	    	if($scope.catalogochoosed == 9){
	    		if($scope.catalogsTable!=undefined )
					 $scope.catalogsTable.reload();
		    	$scope.catalogsTable = new ngTableParams({
		    	       page: 1,
		    	       count: 100
		    	   }, {
		    	       total: $scope.catalogs.length, 
		    	       getData: function ($defer, params) {
		    	    	   $scope.datat = params.sorting() ? $filter('orderBy')($scope.catalogs, params.orderBy()) : $scope.catalogs;
		    	    	   $scope.datat = params.filter() ? $filter('filter')($scope.datat, params.filter()) : $scope.datat;
		    	    	   $scope.datat = $scope.datat.slice((params.page() - 1) * params.count(), params.page() * params.count());
		    	    	   $defer.resolve($scope.datat);
		    	    	}
				   });
	    	}
	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		});
  }
  
  $scope.loadcatalogQuestion = function(id){
		$http.get("/catalogs/getPreguntas?areaId="+$scope.generalData.areachoosed.id+"&subareaId="+$scope.generalData.subareachoosed.id)
	    .then(function(response) {
	    	console.log(response.data)
	    	$scope.preguntas	=	response.data;
	    	if($scope.catalogochoosed == 10){
		    	$scope.catalogs	=	response.data;

	    		if($scope.catalogsTable!=undefined )
					 $scope.catalogsTable.reload();
		    	$scope.catalogsTable = new ngTableParams({
		    	       page: 1,
		    	       count: 100
		    	   }, {
		    	       total: $scope.catalogs.length, 
		    	       getData: function ($defer, params) {
		    	    	   $scope.datat = params.sorting() ? $filter('orderBy')($scope.catalogs, params.orderBy()) : $scope.catalogs;
		    	    	   $scope.datat = params.filter() ? $filter('filter')($scope.datat, params.filter()) : $scope.datat;
		    	    	   $scope.datat = $scope.datat.slice((params.page() - 1) * params.count(), params.page() * params.count());
		    	    	   $defer.resolve($scope.datat);
		    	    	}
				   });
	    	}
	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		});
}
  $scope.loadcatalogResponse = function(id){
		$http.get("/catalogs/getRespuestas?preguntaId="+$scope.generalData.preguntachoosed.id)
	    .then(function(response) {
	    	console.log(response.data)
	    	//$scope.preguntas	=	response.data;
	    	if($scope.catalogochoosed == 11){
		    	$scope.catalogs	=	response.data;

	    		if($scope.catalogsTable!=undefined )
					 $scope.catalogsTable.reload();
		    	$scope.catalogsTable = new ngTableParams({
		    	       page: 1,
		    	       count: 100
		    	   }, {
		    	       total: $scope.catalogs.length, 
		    	       getData: function ($defer, params) {
		    	    	   $scope.datat = params.sorting() ? $filter('orderBy')($scope.catalogs, params.orderBy()) : $scope.catalogs;
		    	    	   $scope.datat = params.filter() ? $filter('filter')($scope.datat, params.filter()) : $scope.datat;
		    	    	   $scope.datat = $scope.datat.slice((params.page() - 1) * params.count(), params.page() * params.count());
		    	    	   $defer.resolve($scope.datat);
		    	    	}
				   });
	    	}
	    }, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		});
}
	$scope.loadcatalog = function(id){

		  $scope.subareachoosed		=	false;
		  $scope.questionchoosed	=	false;
		  $scope.responsechoosed	=	false;
		console.log("catalogochooseds: "+$scope.catalogochoosed)
		if($scope.catalogochoosed==9){
			console.log("Id es nueve")
			  $scope.subareachoosed	=	true;
			$http.get("/catalogs/getCatalog?catalog="+$scope.catalogochoosed)
		    .then(function(response) {
		    	console.log(response.data)
		    	$scope.catalogs	=	response.data;
		    	if($scope.catalogsTable!=undefined )
					 $scope.catalogsTable.reload();
		    	$scope.catalogsTable = new ngTableParams({
		    	       page: 1,
		    	       count: 100
		    	   }, {
		    	       total: $scope.catalogs.length, 
		    	       getData: function ($defer, params) {
		    	    	   $scope.datat = params.sorting() ? $filter('orderBy')($scope.catalogs, params.orderBy()) : $scope.catalogs;
		    	    	   $scope.datat = params.filter() ? $filter('filter')($scope.datat, params.filter()) : $scope.datat;
		    	    	   $scope.datat = $scope.datat.slice((params.page() - 1) * params.count(), params.page() * params.count());
		    	    	   $defer.resolve($scope.datat);
		    	    	}
				   });

		    }, function(response) {
		        //Second function handles error
		        $scope.content = "Something went wrong";
		        console.log("Somenthing went wrong")
		      
			});


		}
		else if($scope.catalogochoosed==10){
			console.log("It is inside catalog question choosen")
			  $scope.questionchoosed	=	true;

		}
		else if($scope.catalogochoosed==11){
			console.log("It is inside catalog response choosen")
			  $scope.responsechoosed	=	true;

		}
		else{
		 	$http.get("/catalogs/getCatalog?catalog="+$scope.catalogochoosed)
		    .then(function(response) {
		    	console.log(response.data)
		    	$scope.catalogs	=	response.data;
		    	if($scope.catalogsTable!=undefined )
					 $scope.catalogsTable.reload();
		    	$scope.catalogsTable = new ngTableParams({
		    	       page: 1,
		    	       count: 100
		    	   }, {
		    	       total: $scope.catalogs.length, 
		    	       getData: function ($defer, params) {
		    	    	   $scope.datat = params.sorting() ? $filter('orderBy')($scope.catalogs, params.orderBy()) : $scope.catalogs;
		    	    	   $scope.datat = params.filter() ? $filter('filter')($scope.datat, params.filter()) : $scope.datat;
		    	    	   $scope.datat = $scope.datat.slice((params.page() - 1) * params.count(), params.page() * params.count());
		    	    	   $defer.resolve($scope.datat);
		    	    	}
				   });

		    }, function(response) {
		        //Second function handles error
		        $scope.content = "Something went wrong";
		        console.log("Somenthing went wrong")
		      
			});


		}
		  $http.get("/catalogs/getArea")
		  .then(function(response) {
		  	console.log(response.data)
				$scope.areas	=	response.data;
		  }, function(response) {
		      //Second function handles error
		      $scope.content = "Something went wrong";
		      console.log("Somenthing went wrong")
			}); 
	}
	$scope.remove = function(id){
		console.log("EL id es: "+id)
		  $http.get("/catalogs/removeCatalog?catalog="+$scope.catalogochoosed+"&id="+id)
	    .then(function(response) {
			$window.location.href = '#!catalogoadmin';

		}, function(response) {
	        //Second function handles error
	        $scope.content = "Something went wrong";
	        console.log("Somenthing went wrong")
		}); 
	}
	
	$scope.update = function(id){
		var txt =	document.getElementById("typeTextTable_"+id);
		var val = "";
		if(txt!=null){
			val = txt.value;
			console.log("El valor texto es: "+val)
			console.log("El catalogo choosed: "+$scope.catalogochoosed)
		  $http.get("/catalogs/updateCatalog?catalog="+$scope.catalogochoosed+"&id="+id+"&text="+val)
		    .then(function(response) {
				$window.location.href = '#!catalogoadmin';
	
			}, function(response) {
		        //Second function handles error
		        $scope.content = "Something went wrong";
		        console.log("Somenthing went wrong")
			}); 
		}
		

	}
 	 $scope.save =	function(id){
 		var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9\+\/\=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/\r\n/g,"\n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}

 		 var ppregunta = "null";
 		var spregunta = "null";
 		var tpregunta = "null";
 		var namecatalog = "null";
		if(document.getElementById("ppregunta") != null)
			ppregunta = document.getElementById("ppregunta").value
		if(document.getElementById("spregunta") != null)
			spregunta = document.getElementById("spregunta").value
		if(document.getElementById("tpregunta") != null)
			tpregunta = document.getElementById("tpregunta").value
		if(document.getElementById("namecatalog") != null)
			namecatalog = document.getElementById("namecatalog").value
										
		console.log("  save ");
		  $http.get("/catalogs/addCatalog?catalog="+$scope.catalogochoosed+"&name="+
				  namecatalog+"&areaId="+$scope.generalData.areachoosed.id+"&subareaId="+
				  $scope.generalData.subareachoosed.id+"&preguntaId="+$scope.generalData.preguntachoosed.id
				  +"&ppregunta="+Base64.encode(ppregunta)+
				  "&spregunta="+ Base64.encode(spregunta)+
				  "&tpregunta="+Base64.encode(tpregunta))
		    .then(function(response) {
		    	console.log(response.data)
		 		$window.location.href = '#!catalogoadmin';
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