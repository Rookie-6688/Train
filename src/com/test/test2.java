package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import util.JDBCUtil;

public class test2 {

	public test2() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws Exception {
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i = 0;
		conn=JDBCUtil.getc();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1=df.parse("2019-09-30 07:00:00");
		Date date2=df.parse("2019-09-30 15:00:00");
		
		Calendar cld=Calendar.getInstance();
		cld.setTime(date2);
		cld.set(Calendar.SECOND, -1);
		Date date11= cld.getTime();
		String first1=df.format(date11);
		System.out.println(first1);

		Calendar cld3=Calendar.getInstance();
		cld3.setTime(date1);
		cld3.add(Calendar.SECOND, 1);
		Date date31= cld3.getTime();
		String first2=df.format(date31);
		System.out.println(first2);
		
		String sql="SELECT COUNT(*) FROM ticket_table WHERE TRAINID=? AND ((START_TIME BETWEEN ? AND ?) OR (END_TIME BETWEEN ? AND ?)) AND SEAT_TYPE=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, "k101");
		ps.setString(2, "2019-09-30 07:00:00");
		ps.setString(3, first1);
		ps.setString(4, first2);
		ps.setString(5, "2019-09-30 15:00:00");
		ps.setInt(6, 1);
		rs=ps.executeQuery();
		while(rs.next()&&rs!=null){
			i=rs.getInt("Count(*)");
		}
		System.out.println(i);
	}
}
