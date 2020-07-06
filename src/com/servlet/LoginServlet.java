package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.impl.UserDaoImpl;

/**
 * Servlet implementation class LoginServletServlet
 */
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Object obj=request.getParameter("remember");
		String yanzheng=request.getParameter("yanzheng")!=null?request.getParameter("yanzheng"):"";
		
		String session_code=request.getSession().getAttribute("session_code").toString();
		String remember="";
		if(obj!=null){
			remember=(String) obj;
		}
		UserDaoImpl user=new UserDaoImpl();
		if(!session_code.equals(yanzheng)){
			request.setAttribute("yanzhneg", "验证码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}else{
			if(user.login(username, password)){
				Cookie name=new Cookie("username",java.net.URLEncoder.encode(username));
				Cookie name1=new Cookie("username1",java.net.URLEncoder.encode(username));
				response.addCookie(name1);
				response.addCookie(name);
				Cookie rem=new Cookie("remember",remember);
				if(remember.equals("1")){
					response.addCookie(rem);
				}else{
					name1.setMaxAge(0);
					rem.setMaxAge(0);
					response.addCookie(name1);
					response.addCookie(rem);
				}
				response.sendRedirect("index.jsp");
			}else{
				request.setAttribute("message", "用户名或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
