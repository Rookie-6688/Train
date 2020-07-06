package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;

import util.FindCookie;

/**
 * Servlet implementation class changepw
 */
public class changepw extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String password=request.getParameter("password");
		String newpassword=request.getParameter("newpassword");
		Cookie[] cookies=request.getCookies();
		Cookie cookiename=FindCookie.getCookie("username", cookies);
		String name=java.net.URLDecoder.decode(cookiename.getValue());
		
		
		UserDao user=new UserDaoImpl();
		if(!user.login(name, password)){
			request.setAttribute("password", "原密码输入错误");
			request.getRequestDispatcher("blank.jsp").forward(request, response);
		}else{
			try {
				user.changeps(newpassword, name);
				request.setAttribute("message", "密码修改成功");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
