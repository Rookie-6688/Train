<%@page import="com.dao.impl.TicketDaoImpl"%>
<%@page import="com.dao.TicketDao"%>
<%@page import="com.dao.impl.UserDaoImpl"%>
<%@page import="com.dao.UserDao"%>
<%@page import="util.FindCookie"%>
<%@page import="com.dao.TrainDao"%>
<%@page import="com.dao.impl.TrainDaoImpl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Say Go</title>
</head>
<body>
<%
	Cookie[] cookies=request.getCookies();
	Cookie cookiename=FindCookie.getCookie("username", cookies);
	String name=java.net.URLDecoder.decode(cookiename.getValue());
	UserDao user=new UserDaoImpl();
	int userid=user.getid(name);
	request.getSession().setAttribute("userid", userid);
	
	String trainid=request.getParameter("i");
	
	request.getSession().setAttribute("trainid", trainid);
	
	String type=request.getParameter("type");
	int type1=Integer.parseInt(type);
	String first=(String)request.getSession().getAttribute("first");
	String end=(String)request.getSession().getAttribute("end");
	
	TrainDao train=new TrainDaoImpl();
	TicketDao tickets=new TicketDaoImpl();
	Map<String,List<Integer>> ticket=train.getticket(trainid);
	
	
	int go=Integer.parseInt(request.getSession().getAttribute("go").toString());
	int trainnumber=0;
	int seatnumber=0;
	double price=0;
	
	String date=request.getSession().getAttribute("date").toString();
	/* 符合条件的列车号 */
	List<Integer> list=train.gettrain(date, first, end,go);
	/* 各个列车号所经过的站数 */
	Map<String,Integer> stationnumber=train.stationnumber(list, first, end);
	/* 各个列车所有出发时间 */
	Map<String,List<String>> train_l=train.trainleave(list);
	/* 各个列车经过站 */
	Map<String,List<String>> train_station=train.getstation(list);
	/* 这辆列车所有站离开时间 */
	List<String> leave_list=train_l.get(trainid);
	/* 这辆列车所经过的所有站 */
	List<String> station_list=train_station.get(trainid);
	/* 出发站所对应的索引 */
	int m1=station_list.indexOf(first);
	/* 终点站所对应的索引 */
	int m2=station_list.indexOf(end);
	/* 出发时间 */
	String first_time=leave_list.get(m1);
	/* 终点时间 */
	String end_time=leave_list.get(m2);
	/* 票价 */
	price=stationnumber.get(trainid)*100; 
	
	int num=300-train.getnum(first_time, end_time, trainid);
	if(num<=0){
		response.sendRedirect("noticket.jsp");
		return ;
	}
	/* 获取车票表里面符合条件的退票 */
	List<Integer> idlist=tickets.hasticket(trainid, first, end, first_time, end_time, type1,userid);
 	if(idlist.size()!=0&&idlist!=null){
 		tickets.changeticket(userid, idlist.get(0));
 		request.getSession().setAttribute("tid", idlist.get(0));
 		response.sendRedirect("backsuccess.jsp");
 	}else{
		List<Integer> zuolist=tickets.getyz(trainid, train.getnum(first_time, end_time, trainid));
		int chexiang=zuolist.get(0);
		int zuowei=zuolist.get(1);
		tickets.add(userid,first, end, first_time, end_time, chexiang, zuowei, 1, price, type1, trainid);
		response.sendRedirect("success.jsp"); 
 	}
	
	
	
	
/* 	if(type.equals("1")){
		trainnumber=ticket.get("yingzuo").get(0);
		seatnumber=ticket.get("yingzuo").get(1);
		price=stationnumber.get(trainid)*100; 
	}
 	
	
	List<Integer> idlist=tickets.hasticket(trainid, first, end, first_time, end_time, type1,userid);
 	if(idlist.size()!=0&&idlist!=null){
 		tickets.changeticket(userid, idlist.get(0));
 		request.getSession().setAttribute("tid", idlist.get(0));
 		response.sendRedirect("backsuccess.jsp");
 	}else{
		if(trainnumber==0&&seatnumber==0){
			response.sendRedirect("noticket.jsp");
		}else{
			if(type.equals("1")){
				tickets.add(userid, first, end, first_time, end_time, 1, 301-seatnumber, type1,price, go,trainid);
				if(seatnumber==1){
					if(trainnumber==0){
						tickets.deleyzz(trainid);
					}else{
						tickets.deleyzg(trainid);
						tickets.deleyzx(trainid);
					}
				}else{
					tickets.deleyzz(trainid);
				}
				tickets.deleyzp(trainid);
				tickets.delezzw(trainid);
			}
			response.sendRedirect("success.jsp");
		}
}  */
%>

</body>
</html>