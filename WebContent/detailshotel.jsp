<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.dao.impl.HotelDaoImpl"%>
<%@page import="com.dao.HotelDao"%>
<%@page import="com.dao.impl.UserDaoImpl"%>
<%@page import="com.dao.UserDao"%>
<%@page import="util.FindCookie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
 .font{font-size:25px;}
        #content {
            border: 10px black;
            margin-left:530px;
            width:300px;
            height: 40px;
            border-width: 5px;
            background: black;
            font-size:30px;
            color:white;
            border-radius: 4px; 
        }
</style>
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
    function fileSelect() {
        document.getElementById("fileToUpload").click(); 
    }
    
    function fileSelected() {
     	var path= document.getElementById("fileToUpload").value;
     	window.location.href="index.jsp?path="+path;
    }
  </script>
</head>

<body style="background:url(img/jdbg.jpg);background-repeat:no-repeat ;background-size:100% 100%; background-attachment: fixed;">
<%	
	Cookie[] cookies=request.getCookies();
	Cookie cookiename=FindCookie.getCookie("username", cookies);
	String name=java.net.URLDecoder.decode(cookiename.getValue());
	request.setAttribute("name", name);
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");

	
	UserDao user=new UserDaoImpl();
	if(request.getParameter("path")!=null){
		String path=request.getParameter("path");
		user.addhead(path, name);
	}
	String imgpath="Train/img/ui-sam.jpg";
	
	String path=request.getServletContext().getRealPath("img/head.png");
	if(user.gethead(name,path)){
		imgpath="img/head.png";
	}else{
		imgpath="img/ui-sam.jpg";
	}
	
	
	HotelDao hotel=new HotelDaoImpl();
	int type=Integer.parseInt(request.getParameter("type"));
	int city=Integer.parseInt(request.getParameter("city"));	
	String id=type+city+"";
	List<Map<String,String>> list=hotel.getlist(id);
%>
  <section id="container">
    <!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
    <!--header start-->
    <header class="header black-bg">
      <div class="sidebar-toggle-box">
        <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
      </div>
      <!--logo start-->
      <a href="index.jsp" class="logo"><b>购票</b></a>
      <!--logo end-->
      <div class="nav notify-row" id="top_menu">
        <!--  notification start -->
          <!-- settings start -->
          <li class="dropdown">
              </a>
            <ul class="dropdown-menu extended tasks-bar">
              <div class="notify-arrow notify-arrow-green"></div>
              <li>
                <a href="index.html#">
                  <div class="task-info">
                    <div class="desc">Dashio Admin Panel</div>
                    <div class="percent">40%</div>
                  </div>
                  <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                      <span class="sr-only">40% Complete (success)</span>
                    </div>
                  </div>
                </a>
              </li>
              <li>
                <a href="index.html#">
                  <div class="task-info">
                    <div class="desc">Database Update</div>
                    <div class="percent">60%</div>
                  </div>
                  <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                      <span class="sr-only">60% Complete (warning)</span>
                    </div>
                  </div>
                </a>
              </li>
              <li>
                <a href="index.html#">
                  <div class="task-info">
                    <div class="desc">Product Development</div>
                    <div class="percent">80%</div>
                  </div>
                  <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                      <span class="sr-only">80% Complete</span>
                    </div>
                  </div>
                </a>
              </li>
              <li>
                <a href="index.html#">
                  <div class="task-info">
                    <div class="desc">Payments Sent</div>
                    <div class="percent">70%</div>
                  </div>
                  <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%">
                      <span class="sr-only">70% Complete (Important)</span>
                    </div>
                  </div>
                </a>
              </li>
            </ul>
          </li>
          <!-- settings end -->
          <!-- inbox dropdown start-->
          <li id="header_inbox_bar" class="dropdown">
          </li>
          <!-- inbox dropdown end -->
          <!-- notification dropdown start-->
          <li id="header_notification_bar" class="dropdown">
          </li>
          <!-- notification dropdown end -->
        </ul>
        <!--  notification end -->
      </div>
      <div class="top-menu">
        <ul class="nav pull-right top-menu">
          <li><a class="logout" href="login.jsp">注销</a></li>
        </ul>
      </div>
    </header>
    <!--header end-->
    <!-- **********************************************************************************************************************************************************
        MAIN SIDEBAR MENU
        *********************************************************************************************************************************************************** -->
    <!--sidebar start-->
    <aside>
      <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
         <p class="centered"><img src=<%=imgpath%> class="img-circle" width="80" Onclick="fileSelect()"></p>
           <form id="form_face" enctype="multipart/form-data" style="width:auto;">
 			 <input type="file" name="fileToUpload" id="fileToUpload" Onchange="fileSelected()" style="display:none;">
		   </form>
          <h5 class="centered">${name}</h5>
          <li class="mt">
            <a  href="index.jsp">
              <i class="fa fa-dashboard"></i>
              <span>首页</span>
              </a>
          </li>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-desktop"></i>
              <span>个人信息</span>
              </a>
            <ul class="sub">
              <li><a href="panels.jsp">基本信息</a></li>
              <li><a href="TicketPagingServlet">已购车票</a></li>
              <li><a href="HotelPagingServlet">已购宾馆</a></li>
            </ul>
          </li>
          <li class="sub-menu">
            <a  class="active" href="javascript:;">
              <i class="fa fa-cogs"></i>
              <span>旅游</span>
              </a>
            <ul class="sub">
              <li><a href="grids.jsp">出行</a></li>
              <li><a href="hotel.jsp">住宿</a></li>
            </ul>
          </li>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-book"></i>
              <span>安全中心</span>
              </a>
            <ul class="sub">
              <li><a href="blank.jsp">修改密码</a></li>
            </ul>
          </li>
          </li>
        </ul>
        <!-- sidebar menu end-->
      </div>
    </aside>
    <!--sidebar end-->
    <!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
    <!--main content start-->
    <section id="main-content">
      <section class="wrapper">
        <div class="row mt">
          </div>
          <div class="col-lg-6 col-md-6 col-sm-12">
          </div>
        </div>
        <!--/ row -->
      </section>
      <!-- /wrapper -->
    </section>
    <!-- /MAIN CONTENT -->
    <div id="content">请选择您要入住的宾馆</div>
    </br>
    </br>
   		<table border="1px" style="margin-left:400px" height="200px" width="600px" class="font">
			<tr>
				<td align="center" style="background:#C0C0C0">宾馆名称</td>
				<td align="center" style="background:#C0C0C0">价格/晚</td>
				<td align="center" style="background:#C0C0C0">空房间数</td>
				<td align="center" style="background:#C0C0C0">操作</td>
			</tr>
				<%
				for(int index = 0; index < list.size(); index++){
					Map<String,String> map= list.get(index);
				%>
			<tr>
				<td align="center" style="background:white;opacity:0.6;color:black"><%=map.get("name")%></td>
				<td align="center" style="background:white;opacity:0.6;color:black"><%=map.get("price") %></td>
				<td align="center" style="background:white;opacity:0.6;color:black"><%=map.get("number") %></td>
				<td align="center" style="background:white;opacity:0.6;color:black"><a href="hotelbuy.jsp?id=<%=map.get("id")%>">订购</a></td>
			</tr>
			<% } %>
		</table>
   		
   		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
   		
   		
    <!-- /MAIN CONTENT -->
    <!--main content end-->
    <!--footer start-->
    <footer class="site-footer">
      <div class="text-center">
        <p>
          &copy; Copyrights <strong>Dashio</strong>. All Rights Reserved
        </p>
        <div class="credits">
          <!--
            You are NOT allowed to delete the credit link to TemplateMag with free version.
            You can delete the credit link only if you bought the pro version.
            Buy the pro version with working PHP/AJAX contact form: https://templatemag.com/dashio-bootstrap-admin-template/
            Licensing information: https://templatemag.com/license/
          -->
          Created with Dashio template by <a href="https://templatemag.com/">TemplateMag</a>
        </div>
        <a href="grid.html#" class="go-top">
          <i class="fa fa-angle-up"></i>
          </a>
      </div>
    </footer>
    <!--footer end-->
  </section>
  <!-- js placed at the end of the document so the pages load faster -->
  <script src="lib/jquery/jquery.min.js"></script>

  <script src="lib/bootstrap/js/bootstrap.min.js"></script>
  <script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
  <script src="lib/jquery.scrollTo.min.js"></script>
  <script src="lib/jquery.nicescroll.js" type="text/javascript"></script>
  <!--common script for all pages-->
  <script src="lib/common-scripts.js"></script>
  <!--script for this page-->

</body>

</html>