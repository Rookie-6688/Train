package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.TrainDao;

import util.JDBCUtil;

public class TrainDaoImpl implements TrainDao{
	//获取符合信息的id
	public List<Integer> gettrain(String date,String first,String leave,int value) throws Exception{
		List<Integer> list=new ArrayList<Integer>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtil.getc();
			DateFormat d=new SimpleDateFormat("yyyy-MM-dd");
			Date date1=d.parse(date);
			
			Calendar cld=Calendar.getInstance();
			cld.setTime(date1);
			cld.add(Calendar.DATE, 1);
			Date date11= cld.getTime();
			String date2=d.format(date11);
			
			String sql="SELECT t1.train_id FROM train_time t1,train_time t2 WHERE t2.station_name=? AND t1.TRAIN_ID=t2.TRAIN_ID AND t1.LEAVE_TIME<t2.LEAVE_TIME AND t1.station_name=? AND t1.train_type=? AND (t1.leave_time BETWEEN ? AND ?) GROUP BY train_id;";       //防止出错先进行预处理
			ps=conn.prepareStatement(sql);
			ps.setString(1, leave);
			ps.setString(2, first);
			ps.setInt(3, value);
			ps.setString(4, date);
			ps.setString(5, date2);
			rs=ps.executeQuery();
			while(rs.next()) {
				int o=rs.getInt("train_id"); 
				list.add(o);
			}
			JDBCUtil.release(conn, ps, rs);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, ps, rs);
		}
		return null;
	}
	//列车id,经过站
	public Map<String, List<String>> getstation(List<Integer> l) throws Exception{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		conn=JDBCUtil.getc();
		Map<String,List<String>> map=new HashMap<>();
		List<String> list1;
		TrainDao train=new TrainDaoImpl();
		for(int m:l){
			list1=new ArrayList<>();
			String sql="SELECT station_name from train_time WHERE train_id=? ORDER BY leave_time ASC;";       //防止出错先进行预处理
			ps=conn.prepareStatement(sql);
			ps.setInt(1, m);
			rs=ps.executeQuery();
			while(rs.next()){
				String name=rs.getString("station_name");
				list1.add(name);
			}
			String name=train.getname(m);
			map.put(name, list1);
		}
		JDBCUtil.release(conn, ps, rs);
		return map;
	}
	//获取经过多少站
	public Map<String,Integer> stationnumber(List<Integer> l,String first,String end) throws Exception{
		Map<String,List<String>> map=new HashMap<>();
		Map<String,Integer> mapp=new HashMap<>();
		List<String> list1;
		TrainDao train=new TrainDaoImpl();
		map=train.getstation(l);
		for(String s:map.keySet()){
			list1=map.get(s);
			int a1=list1.indexOf(first);
			int a2=list1.indexOf(end);
			int length=a2-a1;
			mapp.put(s, length);
		}
		return mapp;
	}
	//列车id,出发时间
	public Map<String,List<String>> trainleave(List<Integer> listid) throws Exception{
		Map<String,List<String>> map=new HashMap<>();
		TrainDao train=new TrainDaoImpl();
		for(int id:listid){
			String name=train.getname(id);
			List<String> list=train.getleave(id);
			map.put(name, list);
		}
		return map;
	}
	//通过id获取出发时间
	@Override
	public List<String> getleave(int id) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<String> list=new ArrayList<>();
		conn=JDBCUtil.getc();
		String sql="select leave_time from train_time where train_id=? order by leave_time asc";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, id);
		rs=ps.executeQuery();
		while(rs.next()&&rs!=null){
			String s=rs.getString("leave_time");
			list.add(s);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}
	//列车id,各个车票数
	public List<Integer> getnumber(String trainid) throws Exception{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Integer> list=new ArrayList<>();
		conn=JDBCUtil.getc();
		String sql="select REMAINING_VOTES,SOFT_NUMBER,NO_NUMBER from trains_table where train_id=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, trainid);
		rs=ps.executeQuery();
		while(rs.next()&&rs!=null){
			int i1=rs.getInt(1);
			int i2=rs.getInt(2);
			int i3=rs.getInt(3);
			list.add(i1);
			list.add(i2);
			list.add(i3);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}
	//获取多个id的车票数
	public Map<String,List<Integer>> numberlist(List<String> l) throws Exception{
		Map<String,List<Integer>> map=new HashMap<>();
		TrainDaoImpl train=new TrainDaoImpl();
		for(String s:l){
			List<Integer> list=train.getnumber(s);
			map.put(s, list);
		}
		return map;
	}
	//通过id获取列车id
	public String getname(int id) throws Exception{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String s = null;
		conn=JDBCUtil.getc();
		String sql="select train_id from trains_table where id=?";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, id);
		rs=ps.executeQuery();
		while(rs.next()&&rs!=null){
			s=rs.getString("train_id");
		}
		JDBCUtil.release(conn, ps, rs);
		return s;
	}
	//车票类型,<车厢数,单个车厢票数>
	public Map<String,List<Integer>> getticket(String trainid) throws Exception{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Map<String,List<Integer>> map=new HashMap<>();
		List<Integer> list1=new ArrayList<>();
		List<Integer> list2=new ArrayList<>();
		List<Integer> list3=new ArrayList<>();
		conn=JDBCUtil.getc();
		String sql="select * from trains_table where train_id=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, trainid);
		rs=ps.executeQuery();
		while(rs.next()&&rs!=null){
			//硬座
			int CARRIAGE_ID=rs.getInt("CARRIAGE_ID");
			int SEAT_ID=rs.getInt("SEAT_ID");
			list1.add(CARRIAGE_ID);
			list1.add(SEAT_ID);
			map.put("yingzuo", list1);
			//软卧
			int SOFTC_NUMBER=rs.getInt("SOFTC_NUMBER");
			int SOFTS_NUMBER=rs.getInt("SOFTS_NUMBER");
			list2.add(SOFTC_NUMBER);
			list2.add(SOFTS_NUMBER);
			map.put("ruanwo", list2);
			//无座
			int NOC_NUMBER=rs.getInt("NOC_NUMBER");
			int NOS_NUMBER=rs.getInt("NOS_NUMBER");
			list3.add(NOC_NUMBER);
			list3.add(NOS_NUMBER);
			map.put("wuzuo", list3);
		}
		JDBCUtil.release(conn, ps, rs);
		return map;
	}
	
	
	
	public int getnum(String first,String end,String trainid) throws Exception{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i = 0;
		conn=JDBCUtil.getc();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1=df.parse(first);
		Date date2=df.parse(end);
		
		Calendar cld=Calendar.getInstance();
		cld.setTime(date2);
		cld.set(Calendar.SECOND, -1);
		Date date11= cld.getTime();
		String first1=df.format(date11);

		Calendar cld3=Calendar.getInstance();
		cld3.setTime(date1);
		cld3.add(Calendar.SECOND, 1);
		Date date31= cld3.getTime();
		String first2=df.format(date31);
		
		String sql="SELECT COUNT(*) FROM ticket_table WHERE TRAINID=? AND ((START_TIME BETWEEN ? AND ?) OR (END_TIME BETWEEN ? AND ?)) AND IF_BACK=0";
		ps=conn.prepareStatement(sql);
		ps.setString(1, trainid);
		ps.setString(2, first);
		ps.setString(3, first1);
		ps.setString(4, first2);
		ps.setString(5, end);
		rs=ps.executeQuery();
		while(rs.next()&&rs!=null){
			i=rs.getInt("Count(*)");
		}
		JDBCUtil.release(conn, ps, rs);
		return i;
	}
	@Override
	public int gettrainnum(String first, String end, String trainid, int type) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i = 0;
		conn=JDBCUtil.getc();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1=df.parse(first);
		Date date2=df.parse(end);
		
		Calendar cld=Calendar.getInstance();
		cld.setTime(date2);
		cld.set(Calendar.SECOND, -1);
		Date date11= cld.getTime();
		String first1=df.format(date11);

		Calendar cld3=Calendar.getInstance();
		cld3.setTime(date1);
		cld3.add(Calendar.SECOND, 1);
		Date date31= cld3.getTime();
		String first2=df.format(date31);
		
		String sql="SELECT COUNT(*) FROM ticket_table WHERE TRAINID=? AND ((START_TIME BETWEEN ? AND ?) OR (END_TIME BETWEEN ? AND ?)) AND SEAT_TYPE=? AND IF_BACK=0";
		ps=conn.prepareStatement(sql);
		ps.setString(1, trainid);
		ps.setString(2, first);
		ps.setString(3, first1);
		ps.setString(4, first2);
		ps.setString(5, end);
		ps.setInt(6, type);
		rs=ps.executeQuery();
		while(rs.next()&&rs!=null){
			i=rs.getInt("Count(*)");
		}
		JDBCUtil.release(conn, ps, rs);
		return i;
	}
	
	public Map<String,String> gettrainleave(int id) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Map<String,String> map=new HashMap<>();
		conn=JDBCUtil.getc();
		String sql="select leave_time,station_name from train_time where train_id=? order by leave_time asc";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, id);
		rs=ps.executeQuery();
		while(rs.next()&&rs!=null){
			String s=rs.getString("leave_time");
			String name=rs.getString("station_name");
			map.put(name, s);
		}
		JDBCUtil.release(conn, ps, rs);
		return map;
	}
	//list<站名,离开时间>
	public List<Map<String,String>> gettrainlist(List<Integer> l) throws Exception{
		List<Map<String,String>> list=new ArrayList<>();
		Map<String,String> map=new HashMap<>();
		TrainDao train=new TrainDaoImpl();
		for(int m:l){
			map=train.gettrainleave(m);
			list.add(map);
		}
		return list;
	}
	
	//开始站离开时间列表
	public List<String> getfirstl(String first,List<Integer> list) throws Exception{
		TrainDao train=new TrainDaoImpl();
		List<Map<String,String>> lll=train.gettrainlist(list);
		List<String> firsttime=new ArrayList<>();
		for(Map<String,String> mapp:lll){
			String s=mapp.get(first);
			firsttime.add(s);
		}
		return firsttime;
	}
	
	//到达站离开时间列表
		public List<String> getendl(String end,List<Integer> list) throws Exception{
			TrainDao train=new TrainDaoImpl();
			List<Map<String,String>> lll=train.gettrainlist(list);
			List<String> endtime=new ArrayList<>();
			for(Map<String,String> mapp:lll){
				String s=mapp.get(end);
				endtime.add(s);
			}
			return endtime;
		}
		//获取出发时间
		public Map<String,String> getleavel(int id) throws Exception {
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			Map<String,String> map=new HashMap<>();
			conn=JDBCUtil.getc();
			String sql="select leave_time,station_name from train_time where train_id=? order by leave_time asc";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()&&rs!=null){
				String s=rs.getString("leave_time");
				String name=rs.getString("station_name");
				map.put(name, s);
			}
			JDBCUtil.release(conn, ps, rs);
			return map;
		}
		
		public int getid(String name) throws Exception{
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			int s = 0;
			conn=JDBCUtil.getc();
			String sql="select id from trains_table where train_id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			rs=ps.executeQuery();
			while(rs.next()&&rs!=null){
				s=rs.getInt("id");
			}
			JDBCUtil.release(conn, ps, rs);
			return s;
		}
}
