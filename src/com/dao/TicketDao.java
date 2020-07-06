package com.dao;

import java.util.List;
import java.util.Map;

public interface TicketDao {
	public int add(int user_id,String train_first,String train_end,String start_time,String end_time,int carriage_id,int seat_id,int seat_type,double ticket_price,int ticket_type,String trainid) throws Exception;
//	public int deleyzx(String trainid) throws Exception;
//	public int deleyzz(String trainid) throws Exception;
//	public int deleyzp(String trainid) throws Exception;
//	public int delerwx(String trainid) throws Exception;
//	public int delerwz(String trainid) throws Exception;
//	public int delerwp(String trainid) throws Exception;
//	public int delewzx(String trainid) throws Exception;
//	public int delewzz(String trainid) throws Exception;
//	public int delewzp(String trainid) throws Exception;
//	public int delezzw(String trainid) throws Exception;
//	public int delewzg(String trainid) throws Exception;
//	public int deleyzg(String trainid) throws Exception;
//	public int delerwg(String trainid) throws Exception;
	public Map<String,String> getticket(int user_id) throws Exception;
	public Map<String,String> getticketback(int user_id) throws Exception;
	public List<Map<String, String>> getlist(int user_id) throws Exception;
	public List<Map<String, String>> getpersonbuy(int user_id,int page,int size) throws Exception;
	public String gettime(int id) throws Exception;
	public int changetype(int id) throws Exception;
	public List<Integer> hasticket(String trainid,String first,String end,String start_time,String end_time,int seat_type,int user_id) throws Exception;
	public int changeticket(int user_id,int id) throws Exception;
	
	public List<Integer> getyz(String trainid,int i) throws Exception;
	public List<Integer> getrw(String trainid, int i) throws Exception;
	public List<Integer> getwz(String trainid, int i) throws Exception;
	public List<List<Integer>> gettrainlist(List<Integer> list,List<String> first,List<String> end) throws Exception;
	public List<Integer> getlist(List<Integer> list,List<String> first,List<String> end) throws Exception;
	
	public int getuser(int user_id) throws Exception;
}
