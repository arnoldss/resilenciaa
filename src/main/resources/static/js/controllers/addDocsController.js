app.controller('addDocsController', ['$scope','ngTableParams','$filter','$window','$http','$base64','$mdDialog','$timeout' ,function($scope,ngTableParams,$filter,$window,$http,$base64,$mdDialog,$timeout,$rootScope) {
	console.log(" addDocs controller");
	$scope.comments	=	"";
	$scope.docName	=	"";
	$scope.comments=	"";
	$scope.iddocumento=	"";
	$scope.generalData	=	{ "docTypeChoosed":"object"};
	$scope.loadSuccess	=	false;
	
	var documentEntity	=	  {
			fileName:"",
			fileComments:"",
			documentName:""
		};
	console.log("EL valor de formSent: "+formSent)
	if(formSent){
		$scope.loadSuccess	=	true;
		$timeout(function () {
			$scope.loadSuccess	=	false;
	    }, 2000);
		

	}
	
  $http.get("/catalogs/getDocuments?idusuariov="+$("#idusuariov").val())
    .then(function(response) {
    	console.log(response.data)
		$scope.docs	=	response.data;
    }, function(response) {
        //Second function handles error
        $scope.content = "Something went wrong";
        console.log("Somenthing went wrong")
	}); //?userId="+$rootScope.idUser
	$http.get("/document/getDocuments?idusuariov="+$("#idusuariov").val())
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
	
	
	
	$scope.save = function (form) {
				/*console.log("Save iddocumento  -----" + $scope.id.id  )
				var docname=$scope.docName;
			    var doctype=$scope.generalData.docTypeChoosed.id;
			    var comments=$scope.comments;
			    console.log("docname :---------  "+$scope.file)*/
			    ///window.location.href = "#!addDocs";
				
			    /*$http.get("/document/attachedImageInfo?docName="+docname+"&comments="+comments+"&docType="+doctype+"&iddocumento="+$scope.id.id)
			    .then(function(response) {
			    	console.log(response.data)
			    	
			    	window.location.href = "#!addDocs";
			    
			    }, function(response) {
			        $scope.content = "Something went wrong";
			        console.log("Somenthing went wrong")
			    });*/
			    
		var id=$("#idusuariov").val();
		$("#idUsuario").val(id);
				
		
	  
	 }
	 
	$scope.remove = function(id,fileName){
		console.log("EL id es: "+id)
		
		
		  $('#deleteFile').modal({
			      backdrop: 'static',
			      keyboard: false
			    })
			    .one('click', '#butttondeleteFile', function(e) {				    			    	 
			    	 $( "div" ).removeClass( "modal-backdrop" )			    	
			    }).one('click', '#xbutttondeleteFile', function(e) {				    			    	 
			    	 $( "div" ).removeClass( "modal-backdrop" )				    	
			    })
			    .one('click', '#butttondeleteFileSi', function(e) {				    			    	 
			    	 $( "div" ).removeClass( "modal-backdrop" )
					    	 $http.get("/document/remove?id="+id+"&fileName="+fileName)
					 	    .then(function(response) {
					 			$window.location.href = '#!addDocs';
		
					 		}, function(response) {
					 	        //Second function handles error
					 	        $scope.content = "Something went wrong";
					 	        console.log("Somenthing went wrong")
					 		}); 
			    })
			    
			    
			    
			    
		 
	}
	
	$scope.download = function(id){
		console.log("Descargando archivo")
		
		 $http.get("/document/downloadFile?idarchivo="+id)
	 	    .then(function(response) {
	 			//$window.location.href = '#!addDocs';

	 		}, function(response) {
	 	        //Second function handles error
	 	        $scope.content = "Something went wrong";
	 	        console.log("Somenthing went wrong")
	 		}); 
	}
 
}]);