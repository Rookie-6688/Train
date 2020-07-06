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
	String first=request.getSession().getAttribute("first").toString();
	String end=request.getSession().getAttribute("end").toString();
	
	TrainDao train=new TrainDaoImpl();
	TicketDao tickets=new TicketDaoImpl();
	
	int go=Integer.parseInt(request.getSession().getAttribute("go").toString());
	String date=request.getSession().getAttribute("date").toString();
	List<Integer> list=train.gettrain(date, first, end,go);
	/* 各个列车经过的站数 */
	Map<String,Integer> stationnumber=train.stationnumber(list, first, end);
	
/* 	int idd=train.getid(trainid);
	Map<String,String> leavell=train.getleavel(idd); */
	/* 各个列车出发时间 */
	Map<String,List<String>> train_l=train.trainleave(list);
	/* 各个列车经过的站 */
	Map<String,List<String>> train_station=train.getstation(list);
	/* 列车各个站离开时间 */
	List<String> leave_list=train_l.get(trainid);
	/* 列车所经过的站 */
	List<String> station_list=train_station.get(trainid);
	int m1=station_list.indexOf(first);
	int m2=station_list.indexOf(end);
	String first_time=leave_list.get(m1);
	String end_time=leave_list.get(m2);
	
	
	double price=0;
	int num=0;
	if(type.equals("1")){
		/* 车票表里面对应的票数 */
		num=1200-train.gettrainnum(first_time, end_time, trainid, 1);
		price=stationnumber.get(trainid)*16; 
	}else if(type.equals("2")){
		num=160-train.gettrainnum(first_time, end_time, trainid, 2);
		price=stationnumber.get(trainid)*32; 
	}else if(type.equals("3")){
		num=600-train.gettrainnum(first_time, end_time, trainid, 3);
		price=stationnumber.get(trainid)*16; 
	}
	
	List<Integer> zuolist=null;
	if(num<=0){
		response.sendRedirect("noticket.jsp");
		return ;
	}
	/* 车票表里面符合条件的 */
	List<Integer> idlist=tickets.hasticket(trainid, first, end, first_time, end_time, type1,userid);
 	if(idlist.size()!=0&&idlist!=null){
 		tickets.changeticket(userid, idlist.get(0));
 		request.getSession().setAttribute("tid", idlist.get(0));
 		response.sendRedirect("backsuccess.jsp");
 	}else{
		if(type.equals("1")){
			zuolist=tickets.getyz(trainid, train.gettrainnum(first_time, end_time, trainid, 1));
		}else if(type.equals("2")){
			zuolist=tickets.getrw(trainid, train.gettrainnum(first_time, end_time, trainid, 2));
		}else if(type.equals("3")){
			zuolist=tickets.getwz(trainid, train.gettrainnum(first_time, end_time, trainid, 3));
		}
		
		int chexiang=zuolist.get(0);
		int zuowei=zuolist.get(1);
		tickets.add(userid, first, end, first_time, end_time, chexiang, zuowei, type1, price, go, trainid);
		response.sendRedirect("success.jsp");
 	}
	
	
	
	
	
	
	
	
	
	
	
	
	/* 
	Map<String,List<Integer>> ticket=train.getticket(trainid);
	
	
	int go=Integer.parseInt(request.getSession().getAttribute("go").toString());
	int trainnumber=0;
	int seatnumber=0;
	double price=0;
	
	String date=request.getSession().getAttribute("date").toString();
	List<Integer> list=train.gettrain(date, first, end,go);
	Map<String,Integer> stationnumber=train.stationnumber(list, first, end);
	
	Map<String,List<String>> train_l=train.trainleave(list);
	Map<String,List<String>> train_station=train.getstation(list);
	List<String> leave_list=train_l.get(trainid);
	List<String> station_list=train_station.get(trainid);
	int m1=station_list.indexOf(first);
	int m2=station_list.indexOf(end);
	String first_time=leave_list.get(m1);
	String end_time=leave_list.get(m2);
	
	if(type.equals("1")){
		trainnumber=ticket.get("yingzuo").get(0);
		seatnumber=ticket.get("yingzuo").get(1);
		price=stationnumber.get(trainid)*16; 
	} else if(type.equals("2")){
		trainnumber=ticket.get("ruanwo").get(0);
		seatnumber=ticket.get("ruanwo").get(1);
		price=stationnumber.get(trainid)*32;
	}else if(type.equals("3")){
		trainnumber=ticket.get("wuzuo").get(0);
		seatnumber=ticket.get("wuzuo").get(1);
		price=stationnumber.get(trainid)*16;
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
				tickets.add(userid, first, end, first_time, end_time, 13-trainnumber, 101-seatnumber, type1,price, go,trainid);
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
			}else if(type.equals("2")){
				tickets.add(userid, first, end, first_time, end_time, 29-trainnumber, 41-seatnumber, type1,price, go,trainid);
				if(seatnumber==1){
					if(trainnumber==13){
						tickets.delerwz(trainid);
					}else{
						tickets.delerwg(trainid);
						tickets.delerwx(trainid);
					}
				}else{
					tickets.delerwz(trainid);
				}
				tickets.delerwp(trainid);
				tickets.delezzw(trainid);
			}else if(type.equals("3")){
				tickets.add(userid, first, end, first_time, end_time, 13-trainnumber, 51-seatnumber, type1,price, go,trainid);
				if(seatnumber==1){
					if(trainnumber==13){
						tickets.delewzz(trainid);
					}else{
						tickets.delewzg(trainid);
						tickets.delewzx(trainid);
					}
				}else{
					tickets.delewzz(trainid);
				}
				tickets.delewzp(trainid);
		}
			response.sendRedirect("success.jsp");
	}
}  */
%>

</body>
</html>