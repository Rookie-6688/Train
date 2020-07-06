package com.dao;

import java.util.List;
import java.util.Map;

public interface HotelDao {
	public List<Map<String, String>> getlist(String id) throws Exception;
	public boolean deone(int id) throws Exception;
	public boolean addhotel(String username,int hotel_id,String hotel_name,double price,String address) throws Exception;
	public Map<String,String> getone(int id) throws Exception;
	public List<Map<String, String>> getuser(String name) throws Exception;
	public List<Map<String, String>> gethotel(String name,int page,int size) throws Exception;
	public int gethoteltotalcount(String name) throws Exception;
	public int getcount(String name) throws Exception;
}
