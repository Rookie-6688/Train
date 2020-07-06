<%@page import="util.FindCookie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Say Go</title>

  <!-- Favicons -->
  <link href="img/favicon.png" rel="icon">
  <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Bootstrap core CSS -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!--external css-->
  <link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles for this template -->
  <link href="css/style.css" rel="stylesheet">
  <link href="css/style-responsive.css" rel="stylesheet">
  
  <!-- =======================================================
    Template Name: Dashio
    Template URL: https://templatemag.com/dashio-bootstrap-admin-template/
    Author: TemplateMag.com
    License: https://templatemag.com/license/
  ======================================================= -->
</head>

<body>
<%
	Cookie[] cookies=request.getCookies();
	String remember="";
	String username="";
	String password="";
	if(cookies!=null&&cookies.length>0){
		for(Cookie c:cookies){
			if(c.getName().equals("remember")){
				remember=c.getValue();
			}else if(c.getName().equals("username1")){
				username=java.net.URLDecoder.decode(c.getValue());
			}
		}
	}
		request.setAttribute("username", username);
		request.setAttribute("remember", remember);
%> 
  <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
  <div id="login-page">
    <div class="container">
      <form class="form-login" action="LoginServlet" method="post">
        <h2 class="form-login-heading">登录</h2>
        <div class="login-wrap">
        <font style="color:red">${message}</font>
        <font style="color:red">${register}</font>
          <input type="text" class="form-control" name="username" placeholder="用户名" autofocus value="${username}">
          <br>
          <input type="password" class="form-control" name="password" placeholder="密码">
          <br/>
          <img src="kaptcha.jpg" id="kaptchaImage" style="width:120px;height:30px" />
          <span id="yanzheng_message" style="color:red"></span>
          <font style="color:red">${yanzhneg}</font>
          <input type="text" class="form-control" width="100px" name="yanzheng" id="yanzheng" Onkeyup="checkyanzheng()" placeholder="*验证码">
          <br>
          <input type="radio" name="radiorole1" checked>学生</input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="radiorole1" >企业</input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="radiorole1" >管理员</input>
          <label class="checkbox">
            <input type="checkbox" name="remember" ${remember eq "1"? 'checked="checked"':''} value="1"> 记住我
            <span class="pull-right">
            <a data-toggle="modal" href="login.html#myModal"> 忘记密码?</a>
            </span>
            </label>
          <button class="btn btn-theme btn-block" href="index.html" type="submit"><i class="fa fa-lock"></i>登录</button>
          <hr>
          <div class="registration">
            还没有账号?<br/>
            <a class="" href="register.jsp">
              注册一个账号
              </a>
          </div>
        </div>
        <!-- Modal -->
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Forgot Password ?</h4>
              </div>
              <div class="modal-body">
                <p>Enter your e-mail address below to reset your password.</p>
                <input type="text" name="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">
              </div>
              <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                <button class="btn btn-theme" type="button">Submit</button>
              </div>
            </div>
          </div>
        </div>
        <!-- modal -->
      </form>
    </div>
  </div>
  <!-- js placed at the end of the document so the pages load faster -->
  <script src="lib/jquery/jquery.min.js"></script>
  <script src="lib/bootstrap/js/bootstrap.min.js"></script>
  <!--BACKSTRETCH-->
  <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
  <script type="text/javascript" src="lib/jquery.backstretch.min.js"></script>
  <script>
    $.backstretch("img/login-bg.jpg", {
      speed: 500
    });
  </script>
</body>

</html>