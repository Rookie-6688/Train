package com.test;

import java.util.List;

import com.dao.TicketDao;
import com.dao.impl.TicketDaoImpl;

public class test5 {

	public test5() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws Exception {
		TicketDao ticket=new TicketDaoImpl();
		List<Integer> list=ticket.getyz("K101", 20);
		int a=list.get(0);
		int b=list.get(1);
		System.out.println(a);
		System.out.println(b);
	}
}
