<%@ page import="com.app.resiliencia.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
<style>
* {
  box-sizing: border-box;
}

#inputTableSortingUsers {
  background-image: url('/css/searchicon.png');
  background-position: 10px 10px;
  background-repeat: no-repeat;
  width: 100%;
  font-size: 16px;
  padding: 12px 20px 12px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}

#tableUsersPendingActivate {
  border-collapse: collapse;
  width: 100%;
  border: 1px solid #ddd;
  font-size: 18px;
}

#tableUsersPendingActivate th, #tableUsersPendingActivate td {
  text-align: left;
  padding: 12px;
}

#tableUsersPendingActivate tr {
  border-bottom: 1px solid #ddd;
}

#tableUsersPendingActivate tr.header, #tableUsersPendingActivate tr:hover {
  background-color: #f1f1f1;
}
</style>
<title> </title>
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
 		  <script type="text/javascript">
 		
 		  var  formSent	=	${FORM_SENT};
 		  </script>
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

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		 
</head>
  <body ng-app="resiliencia-main" ng-controller="mainController" class="hold-transition skin-blue sidebar-mini">
   <input type="hidden" id="idusuariov" name="idusuariov">
  
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="#" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>R</b>SL</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Resiliencia</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <div   class="sidebar-toggle" style="cursor:pointer;" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </div>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a  class="dropdown-toggle" style="cursor:pointer;" data-toggle="dropdown">
              <img src="/images/avatar1.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">{{user.name}}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="/images/avatar1.jpg" class="img-circle" alt="User Image">

                <p>
                  {{user.name}}
                  <small>Miembro desde {{user.since}}</small>
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-body">
                <div class="row">
                  <div class="col-xs-4 text-center">
                    <a href="#"> </a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#"> </a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#"> </a>
                  </div>
                </div>
                <!-- /.row -->
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a ng-click="logout()"href="#" class="btn btn-default btn-flat">Salir</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
         <!--  <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li> -->
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="/images/avatar1.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>{{user.name}}</p>
         <!--  <a href="#"><i class="fa fa-circle text-success"></i> Online</a> -->
        </div>
      </div>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li ng-show="isAdmin" class=" open active">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Administrador</span>
            <span class="pull-right-container">
              <i ></i>
            </span>
          </a>
          <ul class=" open active">
            <li class="active"><a href="#!admin" ><i></i>Administrar Usuarios</a></li>
            <li class="active"><a href="#!catalogoadmin" ><i></i>Administrar Catalogos</a></li>
           
          </ul>
    
        </li>
     <% 
     //HttpServletRequest request; 
      session=request.getSession(); 
      int rol=(int)session.getAttribute("rol");
       User user=(User)session.getAttribute("usuario_en_session"); 
      //usuario_en_session
     
      %>
    
    
     
    
       
      
        <li class="treeview menu-open active" style="display: block;">
          <a href="#">
            <i class="fa fa-table"></i> <span>Informaci&oacute;n General</span>
            <span class="pull-right-container">
              <i></i>
            </span>
          </a>
          <ul class=" open active">
                   <li class="active"><a href="#!generalData" ><i></i>Datos Generales</a></li>
                   <li class="active"><a href="#!whoarewe" ><i></i>Qui&eacute;nes Somos</a></li>
                   
                   <li ng-if="showResult" class="active"><a href="#!resultadoEncuestaUser/{{idUser}}" ><i></i>Resultado de Evaluaci&oacute;n</a></li>

                   <% 
			          if(rol==1){
			            if(user.getEncuesta()==true){
			    
						      %>
						              <li  class="active"><a href="#!resultadoEncuestaUser/<%=user.getId() %>" ><i></i>Resultado de Evaluaci&oacute;n</a></li>                 
						                      
						      <%
			          }





			          if(user.getEncuesta()==false){
			                  %>
						             <li  class="active"><a href="#!encuesta" ><i></i>Evaluaci&oacute;n</a></li>              
						                      
						      <%
			          }

			       }

			      %>


			      <%
			          if(rol==0){


			      %>

			                              <li  class="active"><a href="#!encuestaAdmin" ><i></i>Evaluaci&oacute;n</a></li>
			                              <li  class="active"><a href="#!resultadoEncuesta" ><i></i>Resultado de Evaluaci&oacute;n</a></li>
			           
			      <% }
			    
			      %>
                   
                   <li class="active"><a href="http://159.89.151.82:81" target="_blank"><i></i>Universidad del Bien</a></li>

          </ul>
        </li>
      
         
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" >
          <div ng-view></div>  		 
  		 <img id="resimg" src="/images/resilienciav2.jpg" width="70%" height="80%"/>
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b>0.9
    </div>
    <strong>Copyright &copy; 2018-2019 <a href="https://adminlte.io"></a>.</strong> All rights
    reserved..
  </footer>

 
 </div>
 
    <script type="text/javascript">
    
    function changeview(title){
     
    	document.getElementById("titulo").innerHTML	=	"title";
    	
    	
    	 
    }
   
    function functionSortingPendingUsersActivation() {
      var input, filter, table, tr, td, i;
      input = document.getElementById("inputTableSortingUsers");
      filter = input.value.toUpperCase();
      table = document.getElementById("tableUsersPendingActivate");
      tr = table.getElementsByTagName("tr");
      for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
          if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
            tr[i].style.display = "";
          } else {
            tr[i].style.display = "none";
          }
        }       
      }
    }
    
    

    </script>

 
 
</body>
</html>