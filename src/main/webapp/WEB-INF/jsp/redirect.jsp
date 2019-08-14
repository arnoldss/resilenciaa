<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <link rel="stylesheet" href="/css/bootstrap.min.css" >
 		 <link href="/css/font-awesome.min.css" rel="stylesheet">
  		 <link href="/css/angular-datatables.css" rel="stylesheet">
		 <link href="/css/ionicons.min.css" rel="stylesheet" />
		 <link href="/css/AdminLTE.min.css" rel="stylesheet" />
		 <link href="/css/_all-skins.min.css" rel="stylesheet" />
		 <link href="/css/jquery-jvectormap.css" rel="stylesheet" />
		 <link href="/css/bootstrap-datepicker.min.css" rel="stylesheet" />
		  <link rel="stylesheet" href="/css/daterangepicker.css">
		  <link rel="stylesheet" href="/css/font.css">
		  <link rel="stylesheet" href="/css/jquery.datatables.min.css">
 		 
		<script src="/js/pikaday.js"></script>
		<script src="/js/jquery.min.js"></script>
		<script src="/js/jquery-ui.min.js"></script>
		<script src="/js/bootstrap.min.js"></script>
		<script src="/js/adminlte.min.js"></script>
		<script src="/js/daterangepicker.js"></script>
		<script src="/js/jquery.dataTables.min.js"></script>
		<script src="/js/angular1.6.4.min.js"></script>  
		 <script src="/js/angular-animate.min.js"></script>
		 <script src="/js/angular-aria.min.js"></script>
		 <script src="/js/angular-messages.min.js"></script>
		 <script src="/js/angular-material.min.js"></script>  
		 <script src="/js/angular-route1.6.4.js"></script>  
		 <link rel="stylesheet" href="/css/angular-material-1.9.css" >
 		 <script src="/js/angular-datatables.min.js"></script>
		 <link rel="stylesheet" href="/css/ng-table.min.css">
		 <script src="/js/ng-table.min.js"></script>  
	   	 <script src="/js/angular-base64.js"></script>
		 <script src="/js/routing/routing-main.js"></script>
		 <script src="/js/controllers/mainController.js"></script>
		 <script src="/js/controllers/adminController.js"></script>
 		 <script src="/js/controllers/addDocsController.js"></script>
         <script src="/js/controllers/generalDataController.js"></script>
         <script src="/js/controllers/whoareweController.js"></script>
         <script src="/js/controllers/sustentabilidadController.js"></script>
         <script src="/js/controllers/consejoController.js"></script>
         <script src="/js/controllers/catalogoadminController.js"></script>
         <script src="/js/controllers/encuestaController.js"></script>
         <script src="/js/controllers/encuestaControlerReporte.js"></script>

    	 <link href="/css/angular-bootstrap-toggle.min.css"
                    rel="stylesheet">            
    	 <script src="/js/angular-bootstrap-toggle.min.js"></script>

<script>
$( document ).ready(function() {
             $('#modalRedirectDocuments').modal({
			      backdrop: 'static',
			      keyboard: false
			    })
			    .one('click', '#buttonRedirectDocuments', function(e) {				    			    	 
			    	 $( "div" ).removeClass( "modal-backdrop" )
			    	
			    	  
			    }).one('click', '#xbuttonRedirectDocuments', function(e) {				    			    	 
			    	 $( "div" ).removeClass( "modal-backdrop" )
			    	
			    	
			    })
			    
			    
			    
			    
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


</head>
<body>







<!-- Modal -->
<div  class="modal fade" id="modalRedirectDocuments" tabindex="-1" style="display: none" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content" id="xxxxmodalRedirectDocuments">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"></h5>
        
      
       
      </div>
      <div class="modal-body">
             El documento se guardo correctamente presiones Regresar a documentos para continuar
      </div>
              
             <a class="btn btn-secondary" href="http://142.93.27.154:8081/main2#!/addDocs" ><i></i>Regresar a documentos</button> </a>         
               
      
    </div>
  </div>
</div>
</body>
</html>