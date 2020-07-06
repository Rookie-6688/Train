package com.test;

import java.util.List;

import com.dao.TicketDao;
import com.dao.TrainDao;
import com.dao.impl.TicketDaoImpl;
import com.dao.impl.TrainDaoImpl;

public class test4 {

	public test4() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws Exception {
		TicketDao ticket=new TicketDaoImpl();
		String date="2019-09-30";
		String first="北京";
		String end="上海";
		int go=0;
		TrainDao train=new TrainDaoImpl();
		List<Integer> list=train.gettrain(date, first, end,go);
		
		List<String> firstleave=train.getfirstl(first, list);
		/* 终点站离开时间列表*/
		List<String> endleave=train.getendl(end, list);
		List<List<Integer>> ll=ticket.gettrainlist(list, firstleave, endleave);
		for(List<Integer> l:ll){
			System.out.println(l.get(0));
		}
	}
}
