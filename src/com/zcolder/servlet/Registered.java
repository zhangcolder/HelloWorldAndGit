package com.zcolder.servlet;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcolder.dao.RegisteredDao;

/**
 * Servlet implementation class Registered
 */
@WebServlet("/Registered")
public class Registered extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registered() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();
		/**
		 * registeredInfo
		 * 用来存用户注册的信息的字符串数组
		 */
		String[] registeredInfo = new String[4];
		registeredInfo[0] = request.getParameter("r_useremail");
		registeredInfo[1] = request.getParameter("r_username");
		registeredInfo[2] = request.getParameter("r_password1");
		registeredInfo[3] = request.getParameter("r_password2");
		String test = RegisteredDao.addUser(registeredInfo);
		if(test.equals("注册成功")) {
			response.sendRedirect("./index.html");
		}else {
			response.sendRedirect("./registered.html");
		}
	}					
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
