package com.dao;

import java.util.Map;


public interface UserDao {
//	List<User> findAll();
	boolean login(String name,String password);
	int insert(String namr,String password,String tel,String sex,String home,String date);
	int delete(int id);
	boolean register(String name);
	boolean tel(String tel);
//	int uppdate(String name,String password);
	public int getid(String name);
	public int addhead(String path, String username)throws Exception;
	public boolean gethead(String username,String path) throws Exception;
	public int changeps(String password,String name) throws Exception;
	public Map<String,String> getall(String name) throws Exception;
}
