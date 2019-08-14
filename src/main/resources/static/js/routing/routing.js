var app = angular.module("resiliencia", ["ngRoute",'ngMaterial','base64']);
app.config(function($routeProvider) {
	console.log("inside ngroute")
    $routeProvider
    .when("/", {
        templateUrl : "html/login.htm",
        controller  : 'loginController'
    })
     .when("/recoverPwd", {
        templateUrl : "html/recoverPwd.htm",
        controller  : 'recoverPwdController'
    })
    .when("/register", {
        templateUrl : "html/register.htm",
        controller  : 'registerController'
    })  .when("/convenio", {
        templateUrl : "html/convenio.htm"
    });;
});

 