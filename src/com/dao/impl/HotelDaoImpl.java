package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.HotelDao;

import util.JDBCUtil;

public class HotelDaoImpl implements HotelDao{

	@Override
	public List<Map<String, String>> getlist(String id) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Map<String,String> map;
		List<Map<String, String>> list=new ArrayList<>();
		conn=JDBCUtil.getc();
		String sql="select * from hotel_table where STATIONID=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, id);
		rs=ps.executeQuery();
		while(rs.next()){
			map=new HashMap<>();
			String name=rs.getString("name");
			String price=rs.getDouble("price")+"";
			String number=rs.getString("number");
			String sid=rs.getString("id");
			String address=rs.getString("address");
			map.put("name", name);
			map.put("price", price);
			map.put("number", number);
			map.put("id", sid);
			map.put("address", address);
			list.add(map);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}

	@Override
	public boolean deone(int id) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		conn=JDBCUtil.getc();
		String sql="update hotel_table set number=number-1 where id=?";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, id);
		int i=ps.executeUpdate();
		JDBCUtil.release(conn, ps, null);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean addhotel(String username, int hotel_id, String hotel_name, double price, String address)
			throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		conn=JDBCUtil.getc();
		String sql="insert into hotel(username,hotel_id,hotel_name,price,address) values(?,?,?,?,?)";
		ps=conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setInt(2, hotel_id);
		ps.setString(3, hotel_name);
		ps.setDouble(4, price);
		ps.setString(5, address);
		int i=ps.executeUpdate();
		JDBCUtil.release(conn, ps, null);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public Map<String, String> getone(int id) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Map<String,String> map=null;
		conn=JDBCUtil.getc();
		String sql="select * from hotel_table where id=?";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, id);
		rs=ps.executeQuery();
		while(rs.next()){
			map=new HashMap<>();
			String name=rs.getString("name");
			String price=rs.getDouble("price")+"";
			String number=rs.getString("number");
			String sid=rs.getString("id");
			String address=rs.getString("address");
			map.put("name", name);
			map.put("price", price);
			map.put("number", number);
			map.put("id", sid);
			map.put("address", address);
		}
		JDBCUtil.release(conn, ps, rs);
		return map;
	}
	@Override
	public List<Map<String, String>> getuser(String name) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Map<String,String> map=null;
		List<Map<String, String>> list=new ArrayList<>();
		conn=JDBCUtil.getc();
		String sql="select * from hotel where username=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, name);
		rs=ps.executeQuery();
		while(rs.next()){
			map=new HashMap<>();
			String hotelname=rs.getString("hotel_name");
			String price=rs.getDouble("price")+"";
			String address=rs.getString("address");
			map.put("hotelname", hotelname);
			map.put("price", price);
			map.put("address", address);
			list.add(map);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}
	public List<Map<String, String>> gethotel(String name,int page,int size) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Map<String,String> map=null;
		List<Map<String, String>> list=new ArrayList<>();
		conn=JDBCUtil.getc();
		String sql="select * from hotel where username=? limit ?,?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, name);
		ps.setInt(2, page);
		ps.setInt(3, size);
		rs=ps.executeQuery();
		while(rs.next()){
			map=new HashMap<>();
			String hotelname=rs.getString("hotel_name");
			String price=rs.getDouble("price")+"";
			String address=rs.getString("address");
			map.put("hotelname", hotelname);
			map.put("price", price);
			map.put("address", address);
			list.add(map);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}
	public int gethoteltotalcount(String name) throws Exception{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select count(*) from hotel where username=?";
		int i=0;
		ps=conn.prepareStatement(sql);
		ps.setString(1, name);
		rs = ps.executeQuery();
		i = rs.getInt(1);
		return i;
	}
	public int getcount(String name) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Map<String,String> map=null;
		List<Map<String, String>> list=new ArrayList<>();
		conn=JDBCUtil.getc();
		String sql="select * from hotel where username=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, name);
		rs=ps.executeQuery();
		int i =0;
		while(rs.next()){
         i = i+1;
		}
		JDBCUtil.release(conn, ps, rs);
		return i;
	}

}
