app.controller('encuestaController' ,function($scope,ngTableParams,$filter,$window,$http,$rootScope,$mdDialog,$timeout) {
	console.log("Inside encuestaController  ");
  $scope.encuesta	=	{};
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
  
  var totalDePreguntas;
  

  $( "#resimg" ).remove();
  
  $http.get("/contestaEncuesta/getValidaIsEncuesta")
  .then(function(response) {
	var  isResponseEncuesta=response.data;
	
	if (isResponseEncuesta > 0 ){	
		
		   
		   $( "#titulo" ).hide();	   
		
			
		   $('#modalEncuestaExistente').modal({
			      backdrop: 'static',
			      keyboard: false
			    })
			    .one('click', '#butttonModalEncuestaExistente', function(e) {				    			    	 
			    	 $( "div" ).removeClass( "modal-backdrop" )
			    	window.location.href = "#"; 
			    }).one('click', '#xbutttonModalEncuestaExistente', function(e) {				    			    	 
			    	 $( "div" ).removeClass( "modal-backdrop" )
				    	window.location.href = "#"; 
			    })
			    
		   
	   }else{
		   
		   $http.get("/catalogs/getEncuesta")
		   .then(function(response) {
		   	console.log(response.data)
		 		$scope.encuesta	=	response.data;		   	
		   	//console.log(JSON.stringify($scope.encuesta))
		   }, function(response) {
		       //Second function handles error
		       $scope.content = "Something went wrong";
		       console.log("Somenthing went wrong")
		 	}); 
		   
	   }
	
	
	
   	console.log("El usuario ya cuenta con una evaluacion  :"+isResponseEncuesta)

  }, function(response) {
      //Second function handles error
      $scope.content = "Something went wrong";
      console.log("Somenthing went wrong")
  });
  
  
   
  
  

  
	 
	 $http.get("/contestaEncuesta/getCountPreguntas")
	  .then(function(response) {
		  totalDePreguntas=response.data;
	   	console.log("Total de preguntas  :"+totalDePreguntas)

	  }, function(response) {
	      //Second function handles error
	      $scope.content = "Something went wrong";
	      console.log("Somenthing went wrong")
	  });
  
  
	
 	 $scope.save =	function(id){
 		 
 		//$('#topPage').text(this.'#topPage');
 		 
 		var checkedResponse = {
 			    id:"",
  			    number:0
  			};
 		var listaChecked = [];
 		 var larea	= $scope.encuesta.area.length;
 		 //console.log("Longitud area: "+larea);
 		 for( areas=0; areas<larea;areas++){
 			 var lsubarea = $scope.encuesta.area[areas].subarea.length;
 			 //console.log("lsubarea: "+lsubarea)
 			 for( sareas=0; sareas<lsubarea;sareas++){
 				 
 				var lpregunta = $scope.encuesta.area[areas].subarea[sareas].pregunta.length; 				
 	 			 //console.log("lpregunta: "+lpregunta)
 	 			 for( preguntait=0; preguntait<lpregunta;preguntait++){ 	 				
 	 	 			 var lresponse = $scope.encuesta.area[areas].subarea[sareas].pregunta[preguntait].respuesta.length; 	 	 			
 	 	 			 //console.log("lresponse: "+lresponse)
 	 	 			
 	 	 			for( responseit=0; responseit<lresponse;responseit++){
 	 	 	 			 var id = $scope.encuesta.area[areas].subarea[sareas].pregunta[preguntait].respuesta[responseit].id;
 	 	 	 			 var name = $scope.encuesta.area[areas].subarea[sareas].pregunta[preguntait].respuesta[responseit].name;
 	 	 	 			 var number = $scope.encuesta.area[areas].subarea[sareas].pregunta[preguntait].respuesta[responseit].number;
 	 	 	 			 var comentario=$('#comentario_'+id).val();//"comentario "+id;
 	 	 	 			
 	 	 	 			console.log("Values "+$('#comentario_'+id).val() + "    : comentario_"+id)
 	 	 	 			 
 	 	 	 			 var ele = document.getElementById(id);
 	 	 	 			 if(ele!=null){
 	 	 	 				 if(ele.checked){
 	 	 	 	 	 			console.log("Is checked: Id"+id+ " name: "+name+" number: "+number)
 	 	 	 	 	 		var checkedResponse = new Object();
 	 	 	 	 	 		checkedResponse.id = id;
 	 	 	 	 	 		checkedResponse.number = number;
 	 	 	 	 	 	    checkedResponse.comentario=comentario;
 	 	 	 	 	 		listaChecked.push(checkedResponse);
 	 	 	 				 }
 	 	 	 			 }
 	 	 	 		 }
 	 	 		 }
 	 			
 	 		 }
 		 }
 		 
 		
 		 
 		 
 		 
 	
 		
 		
 		
 		 
 		 console.log("Preguntas contestadas .. :" + listaChecked.length)
 		 var objecttosend = new Object();
 		 objecttosend.lista =	listaChecked;
		 console.log(JSON.stringify(objecttosend));
		 
		 if(totalDePreguntas==listaChecked.length){
			   $http.post("/contestaEncuesta/saveEncuesta", JSON.stringify(listaChecked));	
			   
			  
			   
			   $('#divContainerMain').hide();			   
			   $('#modalRespondeEncuestaOk').modal({
				      backdrop: 'static',
				      keyboard: false
				    })
				    .one('click', '#closeModalEncuestaOk', function(e) {				    			    	 
				    	 $( "div" ).removeClass( "modal-backdrop" )
				    	 window.location.href = "#";
				    	  
				    }).one('click', '#xcloseModalEncuestaOk', function(e) {				    			    	 
				    	 $( "div" ).removeClass( "modal-backdrop" )
				    	 window.location.href = "#";
				    })
			   
				    $('body').removeClass("modal-open")
			   	 	$('body').css("padding-right",""); 
			   
		 }else{
			 
			 //$('#modalRespondeEncuesta').css("margin-top", $(window).height() / 2 - $('.modal-content').height() / 2);
			 //$('#modalRespondeEncuesta').css("margin-top", 97/100*$(window).height()+"%");
			 $('#modalRespondeEncuesta').modal({
			      backdrop: 'static',
			      keyboard: false
			    })
			    .one('click', '#closeModalEncuesta', function(e) {				    			    	 
			    	 $( "div" ).removeClass( "modal-backdrop" )
			    	  
			    }).one('click', '#xcloseModalEncuesta', function(e) {				    			    	 
			    	 $( "div" ).removeClass( "modal-backdrop" )
			    	
			    })
			 
			 
		    	$('body').removeClass("modal-open")
		   	 	$('body').css("padding-right",""); 
		 }
		
		 
		 
		 
		
	 
		 
		 
		
		 
		 
		 
		 
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
	
	
	
	
	 $scope.clenaTextArea =	function(text,id,idpregunta){			 
		 
		 //console.log("Id de pregunta  :  " +idpregunta)
		 
		 if(text=='No Aplica'){		
					 
			 $("#comentario_"+id ).removeAttr("disabled");
			 $("#comentario_"+id ).show();
			 $('#noAplicaIdden_preguntaActual_'+idpregunta).val(id);			 
			 $('#actualAplicaIdden_preguntaActual_'+idpregunta).val(id);
			 
		 }else{	
			 
			 
			 //$('#noAplicaIdden_preguntaActual_'+idpregunta).val();
			 $('#actualAplicaIdden_preguntaActual_'+idpregunta).val(id);
			 
			 
			 if($('#noAplicaIdden_preguntaActual_'+idpregunta).val()!=$('#actualAplicaIdden_preguntaActual_'+idpregunta).val()){				 
				 $('#comentario_'+$('#noAplicaIdden_preguntaActual_'+idpregunta).val()).val('');
				 $('#comentario_'+$('#noAplicaIdden_preguntaActual_'+idpregunta).val()).attr("disabled","disabled");
				 $('#comentario_'+$('#noAplicaIdden_preguntaActual_'+idpregunta).val() ).hide();
				 $('#noAplicaIdden_preguntaActual_'+idpregunta).val(id);
				 
				 
				 
			 }
			 
			 console.log("actualAplicaIdden_preguntaActual  : "+ $('#actualAplicaIdden_preguntaActual_'+idpregunta).val() + " ---  : noAplicaIdden_preguntaActual  : " +$('#noAplicaIdden_preguntaActual_'+idpregunta).val())
			 
		 }
		 
		 
		 
		 
	 }
	
	
	
 
});