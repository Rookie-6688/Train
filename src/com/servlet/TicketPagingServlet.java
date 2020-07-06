package com.servlet;

import java.awt.print.Book;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.dao.impl.TicketDaoImpl;
import com.dao.impl.UserDaoImpl;

import util.FindCookie;
import util.Pager;


/**
 * Servlet implementation class TicketPagingServlet
 */
public class TicketPagingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketPagingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		UserDao user = new UserDaoImpl();
		Cookie[] cookies=request.getCookies();
		Cookie cookiename=FindCookie.getCookie("username", cookies);
		String name=java.net.URLDecoder.decode(cookiename.getValue());
		int userid=user.getid(name);
		String requestPage = request.getParameter("requestPage"); //页面传递请求的是哪一页
		requestPage = requestPage != null && requestPage.length() > 0 ? requestPage : "1";
		
		TicketDaoImpl dao = new TicketDaoImpl();
		try {
			int totalCount = dao.getuser(userid);
			Pager pager = new Pager(); //分页工具类对象，包含页面显示的所有分页数据
			pager.init(requestPage, 20, totalCount); //页面数据初始化			
			//分段取数据
			List<Map<String, String>> list=dao.getpersonbuy(userid,pager.getStartRecord(), pager.getRecordCount());
			request.setAttribute("userList", list);
			request.setAttribute("pager", pager);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("ticket.jsp").forward(request, response);
	}
	}

