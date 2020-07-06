package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCUtil {
	static String[] s= {"com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/train","root","root"};
	//默认的配置对象
	public static Connection getc() {
		try {
			Class.forName(s[0]);
			return DriverManager.getConnection(s[1], s[2], s[3]);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//通过数据数组获取配置对象
	public static Connection getc(String...ss) {
		if(ss==null||ss.length<4) {
			return getc();
		}else {
			s=ss;
			return getc();
		}
	}
	
	public static void close(ResultSet rs,PreparedStatement ps,Statement st,Connection cc) throws Exception {
		if(rs!=null) {
			rs.close();
		}
		if(ps!=null) {
			ps.close();
		}
		if(st!=null) {
			st.close();
		}
		if(cc!=null) {
			cc.close();
		}
	}
	public static void release(Connection conn , Statement st , ResultSet rs){
		closeRs(rs);
		closeSt(st);
		closeConn(conn);
	}
	public static void release(Connection conn , Statement st){
		closeSt(st);
		closeConn(conn);
	}

	
	private static void closeRs(ResultSet rs){
		try {
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			rs = null;
		}
	}
	
	private static void closeSt(Statement st){
		try {
			if(st != null){
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			st = null;
		}
	}
	
	private static void closeConn(Connection conn){
		try {
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn = null;
		}
	}
}
