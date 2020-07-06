package com.test;

import java.util.List;

import com.dao.TicketDao;
import com.dao.impl.TicketDaoImpl;

public class test8 {

	public test8() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws Exception {
		TicketDao ticket=new TicketDaoImpl();
		List<Integer> idlist=ticket.hasticket("K101", "北京", "上海","2019-10-30 08:00:00", "2019-10-30 16:00:00", 1,2);
		System.out.println(idlist.toString());
	}
}
