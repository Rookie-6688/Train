package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.TicketDao;
import com.dao.TrainDao;
import com.dao.impl.TicketDaoImpl;
import com.dao.impl.TrainDaoImpl;

import util.JDBCUtil;

public class test6 {

	public test6() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws Exception {
		TrainDao train=new TrainDaoImpl();
		TicketDao tickets=new TicketDaoImpl();
//		List<Integer> list=train.gettrain("2019-09-30", "成都", "上海",1);
//		Map<String,Integer> stationnumber=train.stationnumber(list, "成都", "上海");
//		
//		Map<String,List<String>> train_l=train.trainleave(list);
//		Map<String,List<String>> train_station=train.getstation(list);
//		List<String> leave_list=train_l.get("C02");
//		List<String> station_list=train_station.get("C02");
//		int m1=station_list.indexOf("成都");
//		int m2=station_list.indexOf("上海");
//		String first_time=leave_list.get(m1);
//		String end_time=leave_list.get(m2);
//		int mmm=train.getnum(first_time, end_time, "C02");
//		List<Integer> zuolist=tickets.getyz("C02", mmm);
//		System.out.println(list.get(0));
//		System.out.println(list.get(1));
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Integer> ll=new ArrayList<>();
		int seat=0;
		conn=JDBCUtil.getc();
		String sql="select seat_id from trains_table where train_id=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, "C02");
		rs=ps.executeQuery();
		while(rs.next()){
			seat=rs.getInt(1);
		}
		
		int m=3/seat;
		int n=3%seat;
		int chexiang1=m+1;
		int zuowei1=n+1;
		ll.add(chexiang1);
		ll.add(zuowei1);
		JDBCUtil.release(conn, ps, rs);
		System.out.println(ll.get(0));
		System.out.println(ll.get(1));
	}
}
