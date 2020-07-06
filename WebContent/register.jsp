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
<script type="text/javascript">
	function checktel(){
		var tel=document.getElementById("tel").value;
		var pattern=/^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/;
		if(!pattern.test(tel)){
			document.getElementById("tel_message").innerHTML="手机号码格式不正确";
			return false;
		}else{
			document.getElementById("tel_message").innerHTML="";
			return true;
		}
	}
	function checkname(){
		var name=document.getElementById("username").value;
		if(name.length<3||name.length>7){
			document.getElementById("name_message").innerHTML="用户名长度必须在3到7位";
			return false;
		}else{
			document.getElementById("name_message").innerHTML="";
			return true;
		}
	}
	function checkpwd(){
		var pattern=/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,13}$/;
		var pwd=document.getElementById("password").value;
		if(!pattern.test(pwd)){
			document.getElementById("pwd_message").innerHTML="密码长度必须在5到13位且必须为数字字母组合";
			return false;
		}else{
			document.getElementById("pwd_message").innerHTML="";
			return true;
		}
	}
	function checkrepwd(){
		var pwd=document.getElementById("password").value;
		var repwd=document.getElementById("repassword").value;
		if(pwd==repwd){
			document.getElementById("repwd_message").innerHTML="";
			return true;
		}else{
			document.getElementById("repwd_message").innerHTML="确认密码必须与密码一致";
			return false;
		}
	}
	function checkyanzheng(){
		var yanzheng=document.getElementById("yanzheng").value;
		if(yanzheng.length=0){
			document.getElementById("yanzheng_message").innerHTML="验证码不能为空";
			return false;
		}else{
			document.getElementById("yanzheng_message").innerHTML="";
			return true;
		}
	}
	function over(){
		if(checktel()&checkname()&checkpwd()&checkyanzheng()){
			return true;
		}else{
			return false;
		}
	}
</script>
</head>

<body>
  <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
  <div id="login-page">
    <div class="container">
      <form class="form-login" action="register" onsubmit="return over()" method="post">
        <h2 class="form-login-heading">注册</h2>
        <div class="login-wrap">
        <font color="color">带*为必填项</font>
       	  <span id="name_message" style="color:red"></span>
       	  <font style="color:red">${message}</font>
          <input type="text" class="form-control" name="username" id="username" Onkeyup="checkname()" placeholder="*用户名" autofocus>
          <br>
          <span id="pwd_message" style="color:red"></span>
          <input type="password" class="form-control" name="password" id="password" Onkeyup="checkpwd()" placeholder="*密码">
          <br>
          <span id="repwd_message" style="color:red"></span>
          <input type="password" class="form-control" name="repassword" id="repassword" Onkeyup="checkrepwd()" placeholder="*确认密码">
          <br>
          <img src="kaptcha.jpg" id="kaptchaImage" style="width:120px;height:30px" />
          <span id="yanzheng_message" style="color:red"></span>
          <font style="color:red">${yanzhneg}</font>
          <input type="text" class="form-control" width="100px" name="yanzheng" id="yanzheng" Onkeyup="checkyanzheng()" placeholder="*验证码">
          <br>
          <span id="tel_message" style="color:red"></span>
          <font style="color:red">${tel}</font>
          <input type="text" class="form-control" name="tel" id="tel" Onkeyup="checktel()" placeholder="*手机号" autofocus>
          <br/>
          性别:&nbsp;&nbsp;<input type="radio" name="sex" value="1">男&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="2">女
          <br>
          <span id="pwd_message" style="color:red"></span>
          <br>
          籍贯:<select name="city" id="city">
          <option value="0">请选择</option>
          <option value="1">北京</option>
          <option value="2">上海</option>
          <option value="3">合肥</option>
          <option value="4">杭州</option>
          <option value="5">深圳</option>
          <option value="6">广州</option>
          <option value="7">其他</option>
          </select>
          <br>
          <br>
          <button class="btn btn-theme btn-block" href="register" type="submit"><i class="fa fa-lock"></i>注册</button>
          <hr><div class="registration">
                <a class="" href="login.jsp">
              返回登录页面
              </a>
              </div>
        </div>
        <!-- Modal -->
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-footer">
              <hr>
                <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                <button class="btn btn-theme" type="button">注册</button>
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