package com.test;

import com.dao.TicketDao;
import com.dao.impl.TicketDaoImpl;

public class test7 {

	public test7() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws Exception{
		TicketDao ticket=new TicketDaoImpl();
		for(int i=0;i<7;i++){
			ticket.add(2, "北京", "上海", "2019-09-30 08:00:00", "2019-09-30 16:00:00", 1, 1, 1, Double.valueOf("50"), 0, "K101");
		}
	}
}
