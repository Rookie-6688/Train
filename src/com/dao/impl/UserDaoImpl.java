package com.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.UserDao;

import util.JDBCUtil;
import util.User;

public class UserDaoImpl implements UserDao{
	@SuppressWarnings("finally")
	@Override
	public boolean login(String name, String password) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtil.getc();
			String sql="select * from user where username=? and password=?";       //防止出错先进行预处理
			ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, ps, rs);
		}
		return false;
	}

	@SuppressWarnings("finally")
	@Override
	public int insert(String name,String password,String tel,String sex,String home,String s) {
		Connection conn=null;
		PreparedStatement ps=null;
		int result=0;
		try {
			conn=JDBCUtil.getc();
			int sexx=Integer.parseInt(sex);
			String sql="insert into user(username,password,tel,sex,city,regist_time) values(?,?,?,?,?,?)";       //防止出错先进行预处理
			ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, tel);
			ps.setInt(4, sexx);
			ps.setString(5, home);
			ps.setString(6, s);
			result=ps.executeUpdate();
			if(result>0) {
				System.out.println("注册成功");
			}else {
				System.out.println("注册失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, ps);
			return result;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public int delete(int id) {
		Connection conn=null;
		PreparedStatement ps=null;
		int result=0;
		try {
			conn=JDBCUtil.getc();
			String sql="delete from user where id=?";       //防止出错先进行预处理
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			result=ps.executeUpdate();
			if(result>0) {
				System.out.println("删除成功");
			}else {
				System.out.println("删除失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, ps);
			return result;
		}
	}
	
	public boolean register(String name){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtil.getc();
			String sql="select * from user where username=?";       //防止出错先进行预处理
			ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			rs=ps.executeQuery();
			if(rs.next()) {
				return false;
			}else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, ps, rs);
		}
		return false;
	}

	@Override
	public boolean tel(String tel) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtil.getc();
			String sql="select * from user where tel=?";       //防止出错先进行预处理
			ps=conn.prepareStatement(sql);
			ps.setString(1, tel);
			rs=ps.executeQuery();
			if(rs.next()) {
				return false;
			}else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, ps, rs);
		}
		return false;
	}

	@Override
	public int getid(String name) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtil.getc();
			String sql="select user_id from user where username=?";       //防止出错先进行预处理
			ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, ps, rs);
		}
		return 0;
	}

	@Override
	public int addhead(String path,String username) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		conn=JDBCUtil.getc();
		String sql="update user set head=? where username=?";       //防止出错先进行预处理
		ps=conn.prepareStatement(sql);
		ps.setBlob(1, new FileInputStream(new File(path)));
		ps.setString(2, username);
		int i=ps.executeUpdate();
		JDBCUtil.release(conn, ps, null);
		return i;
	}

	@Override
	public boolean gethead(String username,String path) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		FileOutputStream on=null;
		InputStream in=null;
		conn=JDBCUtil.getc();
		String sql="select head from user where username=?";       //防止出错先进行预处理
		ps=conn.prepareStatement(sql);
		ps.setString(1, username);
		rs=ps.executeQuery();
//			if(!rs.isFirst()||!rs.isBeforeFirst()||rs.isAfterLast()){
//				return false;
//			}
		while(rs.next()) {
			if(rs.getBlob(1)==null){
				return false;
			}
			int temp=-1;
			on=new FileOutputStream(new File(path));
			in=rs.getBlob(1).getBinaryStream();
			while((temp=in.read())!=-1) {
				on.write(temp);
			}
			on.flush();
			on.close();
			in.close();
		}
		JDBCUtil.release(conn, ps, rs);
		return true;
	}

	@Override
	public int changeps(String password, String name) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		conn=JDBCUtil.getc();
		String sql="update user set password=? where username=?";       //防止出错先进行预处理
		ps=conn.prepareStatement(sql);
		ps.setString(1, password);
		ps.setString(2, name);
		int i=ps.executeUpdate();
		JDBCUtil.release(conn, ps, null);
		return i;
	}

	@Override
	public Map<String, String> getall(String name) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		FileOutputStream on=null;
		InputStream in=null;
		Map<String,String> map=new HashMap<>();
		conn=JDBCUtil.getc();
		String sql="select username,tel,sex,regist_time,city from user where username=?";       //防止出错先进行预处理
		ps=conn.prepareStatement(sql);
		ps.setString(1, name);
		rs=ps.executeQuery();
		while(rs.next()) {
			String username=rs.getString(1);
			String tel=rs.getString(2);
			String sex=rs.getInt(3)+"";
			String time=rs.getString(4);
			String city=rs.getInt(5)+"";
			map.put("username", username);
			map.put("tel", tel);
			map.put("sex", sex);
			map.put("time", time);
			map.put("city", city);
		}
		JDBCUtil.release(conn, ps, rs);
		return map;
	}
}
