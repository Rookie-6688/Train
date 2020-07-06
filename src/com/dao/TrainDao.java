package com.dao;

import java.util.List;
import java.util.Map;

public interface TrainDao {
	public List<Integer> gettrain(String date,String first,String leave,int value) throws Exception;
	public Map<String, List<String>> getstation(List<Integer> l) throws Exception;
	public Map<String,Integer> stationnumber(List<Integer> l,String first,String end) throws Exception;
	public Map<String,List<String>> trainleave(List<Integer> listid) throws Exception;
	public List<String> getleave(int id) throws Exception;
	public List<Integer> getnumber(String trainid) throws Exception;
	public Map<String,List<Integer>> numberlist(List<String> l) throws Exception;
	public String getname(int id) throws Exception;
	public Map<String,List<Integer>> getticket(String trainid) throws Exception;
	public int getnum(String first,String end,String trainid) throws Exception;
	public int gettrainnum(String first,String end,String trainid,int type) throws Exception;
	public Map<String,String> gettrainleave(int id) throws Exception;
	public List<Map<String,String>> gettrainlist(List<Integer> l) throws Exception;
	public List<String> getfirstl(String first,List<Integer> list) throws Exception;
	public List<String> getendl(String end,List<Integer> list) throws Exception;
	public Map<String,String> getleavel(int id) throws Exception;
	public int getid(String name) throws Exception;
}	