package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.TicketDao;
import com.dao.TrainDao;

import util.JDBCUtil;

public class TicketDaoImpl implements TicketDao{

	public int add(int user_id,String train_first,String train_end,String start_time,String end_time,int carriage_id,int seat_id,int seat_type,double ticket_price,int ticket_type,String trainid) throws Exception{
		Connection conn=null;
		PreparedStatement ps=null;
//		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
//		Date date1=df.parse(start_time);
//		Date date2=df.parse(end_time);
//		java.sql.Timestamp date11=new java.sql.Timestamp(date1.getTime());
//		java.sql.Timestamp date22=new java.sql.Timestamp(date2.getTime());
		conn=JDBCUtil.getc();
		String sql="insert into ticket_table(user_id,train_first,train_end,start_time,end_time,carriage_id,seat_id,seat_type,ticket_price,ticket_tyep,trainid,if_back) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, user_id);
		ps.setString(2, train_first);
		ps.setString(3, train_end);
		ps.setString(4, start_time);
		ps.setString(5, end_time);
		ps.setInt(6, carriage_id);
		ps.setInt(7, seat_id);
		ps.setInt(8, seat_type);
		ps.setDouble(9, ticket_price);
		ps.setInt(10, ticket_type);
		ps.setString(11, trainid);
		ps.setInt(12, 0);
		int i=ps.executeUpdate();
		JDBCUtil.release(conn, ps, null);
		return i;
	}
//	//硬座厢
//	public int deleyzx(String trainid) throws Exception{
//		Connection conn=null;
//		PreparedStatement ps=null;
//		conn=JDBCUtil.getc();
//		String sql="update trains_table set CARRIAGE_ID=CARRIAGE_ID-1 where train_id=?";
//		ps=conn.prepareStatement(sql);
//		ps.setString(1, trainid);
//		int i=ps.executeUpdate();
//		JDBCUtil.release(conn, ps, null);
//		return i;
//	}
//	//单个硬座位
//	public int deleyzz(String trainid) throws Exception{
//		Connection conn=null;
//		PreparedStatement ps=null;
//		conn=JDBCUtil.getc();
//		String sql="update trains_table set SEAT_ID=SEAT_ID-1 where train_id=?";
//		ps=conn.prepareStatement(sql);
//		ps.setString(1, trainid);
//		int i=ps.executeUpdate();
//		JDBCUtil.release(conn, ps, null);
//		return i;
//	}
//	//硬座数
//	public int deleyzp(String trainid) throws Exception{
//		Connection conn=null;
//		PreparedStatement ps=null;
//		conn=JDBCUtil.getc();
//		String sql="update trains_table set REMAINING_VOTES=REMAINING_VOTES-1 where train_id=?";
//		ps=conn.prepareStatement(sql);
//		ps.setString(1, trainid);
//		int i=ps.executeUpdate();
//		JDBCUtil.release(conn, ps, null);
//		return i;
//	}
//	//硬座改变成最大值
//	public int deleyzg(String trainid) throws Exception{
//		Connection conn=null;
//		PreparedStatement ps=null;
//		conn=JDBCUtil.getc();
//		String sql="update trains_table set SEAT_ID=100 where train_id=?";
//		ps=conn.prepareStatement(sql);
//		ps.setString(1, trainid);
//		int i=ps.executeUpdate();
//		JDBCUtil.release(conn, ps, null);
//		return i;
//	}
//	//软座厢
//	public int delerwx(String trainid) throws Exception{
//		Connection conn=null;
//		PreparedStatement ps=null;
//		conn=JDBCUtil.getc();
//		String sql="update trains_table set SOFTC_NUMBER=SOFTC_NUMBER-1 where train_id=?";
//		ps=conn.prepareStatement(sql);
//		ps.setString(1, trainid);
//		int i=ps.executeUpdate();
//		JDBCUtil.release(conn, ps, null);
//		return i;
//	}
//	//单个软座数
//	public int delerwz(String trainid) throws Exception{
//		Connection conn=null;
//		PreparedStatement ps=null;
//		conn=JDBCUtil.getc();
//		String sql="update trains_table set SOFTS_NUMBER=SOFTS_NUMBER-1 where train_id=?";
//		ps=conn.prepareStatement(sql);
//		ps.setString(1, trainid);
//		int i=ps.executeUpdate();
//		JDBCUtil.release(conn, ps, null);
//		return i;
//	}
//	//软座数
//	public int delerwp(String trainid) throws Exception{
//		Connection conn=null;
//		PreparedStatement ps=null;
//		conn=JDBCUtil.getc();
//		String sql="update trains_table set SOFT_NUMBER=SOFT_NUMBER-1 where train_id=?";
//		ps=conn.prepareStatement(sql);
//		ps.setString(1, trainid);
//		int i=ps.executeUpdate();
//		JDBCUtil.release(conn, ps, null);
//		return i;
//	}
//	//软座改变成最大值
//	public int delerwg(String trainid) throws Exception{
//		Connection conn=null;
//		PreparedStatement ps=null;
//		conn=JDBCUtil.getc();
//		String sql="update trains_table set SOFTS_NUMBER=100 where train_id=?";
//		ps=conn.prepareStatement(sql);
//		ps.setString(1, trainid);
//		int i=ps.executeUpdate();
//		JDBCUtil.release(conn, ps, null);
//		return i;
//	}
//	//无座厢
//	public int delewzx(String trainid) throws Exception{
//		Connection conn=null;
//		PreparedStatement ps=null;
//		conn=JDBCUtil.getc();
//		String sql="update trains_table set NOC_NUMBER=NOC_NUMBER-1 where train_id=?";
//		ps=conn.prepareStatement(sql);
//		ps.setString(1, trainid);
//		int i=ps.executeUpdate();
//		JDBCUtil.release(conn, ps, null);
//		return i;
//	}
//	//单个无座数
//	public int delewzz(String trainid) throws Exception{
//		Connection conn=null;
//		PreparedStatement ps=null;
//		conn=JDBCUtil.getc();
//		String sql="update trains_table set NOS_NUMBER=NOS_NUMBER-1 where train_id=?";
//		ps=conn.prepareStatement(sql);
//		ps.setString(1, trainid);
//		int i=ps.executeUpdate();
//		JDBCUtil.release(conn, ps, null);
//		return i;
//	}
//	//无座票
//	public int delewzp(String trainid) throws Exception{
//		Connection conn=null;
//		PreparedStatement ps=null;
//		conn=JDBCUtil.getc();
//		String sql="update trains_table set NO_NUMBER=NO_NUMBER-1 where train_id=?";
//		ps=conn.prepareStatement(sql);
//		ps.setString(1, trainid);
//		int i=ps.executeUpdate();
//		JDBCUtil.release(conn, ps, null);
//		return i;
//	}
//	//无座改变成最大值
//	public int delewzg(String trainid) throws Exception{
//		Connection conn=null;
//		PreparedStatement ps=null;
//		conn=JDBCUtil.getc();
//		String sql="update trains_table set NOS_NUMBER=100 where train_id=?";
//		ps=conn.prepareStatement(sql);
//		ps.setString(1, trainid);
//		int i=ps.executeUpdate();
//		JDBCUtil.release(conn, ps, null);
//		return i;
//	}
//	//总座位数
//	public int delezzw(String trainid) throws Exception{
//		Connection conn=null;
//		PreparedStatement ps=null;
//		conn=JDBCUtil.getc();
//		String sql="update trains_table set MAX_SEAT=MAX_SEAT-1 where train_id=?";
//		ps=conn.prepareStatement(sql);
//		ps.setString(1, trainid);
//		int i=ps.executeUpdate();
//		JDBCUtil.release(conn, ps, null);
//		return i;
//	}
	@Override
	public Map<String, String> getticket(int user_id) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Map<String, String> map=new HashMap<>();
		conn=JDBCUtil.getc();
		String sql="select * from ticket_table where user_id=? order by id desc";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, user_id);
		rs=ps.executeQuery();
		if(rs.next()&&rs!=null){
			String first=rs.getString("train_first");
			String end=rs.getString("train_end");
			String start_time=rs.getString("start_time");
			String end_time=rs.getString("end_time");
			String train_station=rs.getString("carriage_id");
			String seat=rs.getString("seat_id");
			String seat_type=rs.getString("seat_type");
			String price=rs.getString("ticket_price");
			String ticket_type=rs.getString("ticket_tyep");
			String trainid=rs.getString("trainid");
			map.put("first", first);
			map.put("end", end);
			map.put("start_time", start_time);
			map.put("end_time", end_time);
			map.put("train_station", train_station);
			map.put("seat", seat);
			map.put("seat_type", seat_type);
			map.put("price", price);
			map.put("ticket_type", ticket_type);
			map.put("trainid", trainid);
		}
		JDBCUtil.release(conn, ps, rs);
		return map;
	}
	@Override
	public Map<String, String> getticketback(int id) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Map<String, String> map=new HashMap<>();
		conn=JDBCUtil.getc();
		String sql="select * from ticket_table where id=?";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, id);
		rs=ps.executeQuery();
		if(rs.next()&&rs!=null){
			String first=rs.getString("train_first");
			String end=rs.getString("train_end");
			String start_time=rs.getString("start_time");
			String end_time=rs.getString("end_time");
			String train_station=rs.getString("carriage_id");
			String seat=rs.getString("seat_id");
			String seat_type=rs.getString("seat_type");
			String price=rs.getString("ticket_price");
			String ticket_type=rs.getString("ticket_tyep");
			String trainid=rs.getString("trainid");
			map.put("first", first);
			map.put("end", end);
			map.put("start_time", start_time);
			map.put("end_time", end_time);
			map.put("train_station", train_station);
			map.put("seat", seat);
			map.put("seat_type", seat_type);
			map.put("price", price);
			map.put("ticket_type", ticket_type);
			map.put("trainid", trainid);
		}
		JDBCUtil.release(conn, ps, rs);
		return map;
	}
	@Override
	public List<Map<String, String>> getpersonbuy(int user_id,int page,int size) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Map<String, String>> list=new ArrayList<>();
		Map<String, String> map=null;
		conn=JDBCUtil.getc();
		String sql="select * from ticket_table where user_id=? limit ?,?";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, user_id);
		ps.setInt(2, page);
		ps.setInt(3, size);
		rs=ps.executeQuery();
		while(rs.next()&&rs!=null){
			map=new HashMap<>();
			String first=rs.getString("train_first");
			String end=rs.getString("train_end");
			String start_time=rs.getString("start_time");
			String end_time=rs.getString("end_time");
			String train_station=rs.getString("carriage_id");
			String seat=rs.getString("seat_id");
			String seat_type=rs.getString("seat_type");
			String price=rs.getString("ticket_price");
			String ticket_type=rs.getString("ticket_tyep");
			String trainid=rs.getString("trainid");
			String id=rs.getString("id");
			
			if(ticket_type.equals("0")){
				ticket_type="火车";
			}else if(ticket_type.equals("1")){
				ticket_type="大巴";
			}else if(ticket_type.equals("2")){
				ticket_type="飞机";
			}

			map.put("id", id);
			map.put("first", first);
			map.put("end", end);
			map.put("start_time", start_time);
			map.put("end_time", end_time);
			map.put("train_station", train_station);
			map.put("seat", seat);
			map.put("seat_type", seat_type);
			map.put("price", price);
			map.put("ticket_type", ticket_type);
			map.put("trainid", trainid);
			list.add(map);
		}
		
		JDBCUtil.release(conn, ps, rs);
		return list;
	}
	
	
	public int getuser(int user_id) throws Exception{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		conn=JDBCUtil.getc();
		String sql="select * from ticket_table where user_id=?";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, user_id);
		rs=ps.executeQuery();
		int u=0;
		while(rs.next()&&rs!=null){
			u=u+1;
		}
		JDBCUtil.release(conn, ps, rs);
		return u;
	}
	
	
	@Override
	public String gettime(int id) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		conn=JDBCUtil.getc();
		String sql="select * from ticket_table where id=?";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, id);
		rs=ps.executeQuery();
		String s="";
		while(rs.next()&&rs!=null){
			s=rs.getString("start_time");
		}
		JDBCUtil.release(conn, ps, rs);
		return s;
	}
	@Override
	public int changetype(int id) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		conn=JDBCUtil.getc();
		String sql="update ticket_table set if_back=1,user_id=0 where id=?";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, id);
		int i=ps.executeUpdate();
		JDBCUtil.release(conn, ps, null);
		return i;
	}
	@Override
	public List<Integer> hasticket(String trainid, String first, String end, String start_time, String end_time,
			int seat_type,int user_id) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Integer> list=new ArrayList<>();
		conn=JDBCUtil.getc();
		String sql="select * from ticket_table where trainid=? and train_first=? and train_end=? and start_time=? and end_time=? and seat_type=? and if_back=1 and user_id!=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, trainid);
		ps.setString(2, first);
		ps.setString(3, end);
		ps.setString(4, start_time);
		ps.setString(5, end_time);
		ps.setInt(6, seat_type);
		ps.setInt(7, user_id);
		rs=ps.executeQuery();
		while(rs.next()&&rs!=null){
			int i=rs.getInt("id");
			list.add(i);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}
	@Override
	public int changeticket(int user_id,int id) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		conn=JDBCUtil.getc();
		String sql="update ticket_table set if_back=0,user_id=? where id=?";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, user_id);
		ps.setInt(2, id);
		int i=ps.executeUpdate();
		JDBCUtil.release(conn, ps, null);
		return i;
	}
	
	
	@Override
	public List<Integer> getyz(String trainid, int i) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Integer> list=new ArrayList<>();
		int seat=0;
		conn=JDBCUtil.getc();
		String sql="select seat_id from trains_table where train_id=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, trainid);
		rs=ps.executeQuery();
		while(rs.next()){
			seat=rs.getInt(1);
		}
		
		int m=i/seat;
		int n=i%seat;
		int chexiang=m+1;
		int zuowei=n+1;
		list.add(chexiang);
		list.add(zuowei);
		JDBCUtil.release(conn, ps, rs);
		return list;
	}
	
	public List<Integer> getrw(String trainid, int i) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Integer> list=new ArrayList<>();
		int seat=0;
		conn=JDBCUtil.getc();
		String sql="select softs_number from trains_table where train_id=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, trainid);
		rs=ps.executeQuery();
		while(rs.next()){
			seat=rs.getInt(1);
		}
		
		int m=i/seat;
		int n=i%seat;
		int chexiang=m+13;
		int zuowei=n+1;
		list.add(chexiang);
		list.add(zuowei);
		JDBCUtil.release(conn, ps, rs);
		return list;
	}
	public List<Integer> getwz(String trainid, int i) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Integer> list=new ArrayList<>();
		int seat=0;
		conn=JDBCUtil.getc();
		String sql="select nos_number from trains_table where train_id=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, trainid);
		rs=ps.executeQuery();
		while(rs.next()){
			seat=rs.getInt(1);
		}
		int m=i/seat;
		int chexiang=m+1;
		int zuowei=0;
		list.add(chexiang);
		list.add(zuowei);
		JDBCUtil.release(conn, ps, rs);
		return list;
	}
	
	//list<需减硬座数,需减软卧数,需减无座数>
	@Override
	public List<List<Integer>> gettrainlist(List<Integer> list,List<String> first,List<String> end) throws Exception {
		TrainDao train=new TrainDaoImpl();
		List<List<Integer>> ll=new ArrayList<>();
		List<Integer> lll;
		String firsttime;
		String endtime;
		for(int i=0;i<list.size();i++){
			lll=new ArrayList<>();
			String trainid=train.getname(list.get(i));
			firsttime= first.get(i);
			endtime=end.get(i);
			int ying=train.gettrainnum(firsttime, endtime, trainid,1);
			int ruan=train.gettrainnum(firsttime, endtime, trainid, 2);
			int wuzuo=train.gettrainnum(firsttime, endtime, trainid, 3);
			lll.add(ying);
			lll.add(ruan);
			lll.add(wuzuo);
			ll.add(lll);
		}
		return ll;
	}
	public List<Integer> getlist(List<Integer> list,List<String> first,List<String> end) throws Exception {
		TrainDao train=new TrainDaoImpl();
		List<Integer> lll=new ArrayList<>();
		String firsttime;
		String endtime;
		for(int i=0;i<list.size();i++){
			String trainid=train.getname(list.get(i));
			firsttime= first.get(i);
			endtime=end.get(i);
			int ying=train.getnum(firsttime, endtime, trainid);
			lll.add(ying);
		}
		return lll;
	}
	@Override
	public List<Map<String, String>> getlist(int user_id) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Map<String, String>> list=new ArrayList<>();
		Map<String, String> map=null;
		conn=JDBCUtil.getc();
		String sql="select * from ticket_table where user_id=?";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, user_id);
		rs=ps.executeQuery();
		while(rs.next()&&rs!=null){
			map=new HashMap<>();
			String first=rs.getString("train_first");
			String end=rs.getString("train_end");
			String start_time=rs.getString("start_time");
			String end_time=rs.getString("end_time");
			String train_station=rs.getString("carriage_id");
			String seat=rs.getString("seat_id");
			String seat_type=rs.getString("seat_type");
			String price=rs.getString("ticket_price");
			String ticket_type=rs.getString("ticket_tyep");
			String trainid=rs.getString("trainid");
			String id=rs.getString("id");
			
			if(ticket_type.equals("0")){
				ticket_type="火车";
			}else if(ticket_type.equals("1")){
				ticket_type="大巴";
			}else if(ticket_type.equals("2")){
				ticket_type="飞机";
			}
			
			map.put("id", id);
			map.put("first", first);
			map.put("end", end);
			map.put("start_time", start_time);
			map.put("end_time", end_time);
			map.put("train_station", train_station);
			map.put("seat", seat);
			map.put("seat_type", seat_type);
			map.put("price", price);
			map.put("ticket_type", ticket_type);
			map.put("trainid", trainid);
			list.add(map);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}
}
