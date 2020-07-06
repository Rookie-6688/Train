package com.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.impl.UserDaoImpl;

/**
 * Servlet implementation class register
 */
public class register extends HttpServlet {
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String tel=request.getParameter("tel");
		String sex=request.getParameter("sex")!=null?request.getParameter("sex"):"10";
		String home=request.getParameter("city")!=null?request.getParameter("city"):"10";
		UserDaoImpl user=new UserDaoImpl();
		String yanzheng=request.getParameter("yanzheng")!=null?request.getParameter("yanzheng"):"";
		
		String session_code=request.getSession().getAttribute("session_code").toString();
		
		if(user.register(username)){
			if(user.tel(tel)){
				if(!session_code.equals(yanzheng)){
					request.setAttribute("yanzhneg", "验证码错误");
					request.getRequestDispatcher("register.jsp").forward(request, response);
					return;
				}else{
					long l=System.currentTimeMillis();
					Date date=new Date(l);
					DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time=df.format(date);
					user.insert(username, password,tel,sex,home,time);
					request.setAttribute("register", "注册成功");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("tel", "一个手机号只能绑定一个用户");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("message", "用户名已存在");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
