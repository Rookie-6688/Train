package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class choice
 */
public class choice extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String first=request.getParameter("first");
		String end=request.getParameter("end");
		String date=request.getParameter("date");
		String go=request.getParameter("go");
		request.getSession().setAttribute("first", first);
		request.getSession().setAttribute("end", end);
		request.getSession().setAttribute("go", go);
		request.getSession().setAttribute("date", date);
		if(go.equals("0")){
			response.sendRedirect("calendar.jsp");
		}else if(go.equals("2")){
			response.sendRedirect("airplane.jsp");
		}else{
			response.sendRedirect("bus.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
