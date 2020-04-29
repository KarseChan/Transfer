package com.Karse.event.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Karse.event.dao.impl.UserDaoImpl;
import com.Karse.event.entity.User;
import com.Karse.event.service.UserService;
import com.Karse.event.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static UserService userService = new UserServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		//页面输出
        PrintWriter out = response.getWriter();
        
        //包装成实体类
        String userName = request.getParameter("name");
        String password = request.getParameter("password");
        User input = new User();
        input.setName(userName);
        input.setPassword(password);
        //数据库返回的实体类
        User user = userService.login(input);
        if(userName==null||"".equals(userName))
        {
            out.print("<script language='javaScript'> alert('用户名不能为空');</script>");
            response.setHeader("refresh", "0;url=user_login.jsp");
        }
        else if(password==null||"".equals(password)){
        	out.print("<script language='javaScript'> alert('密码不能为空');</script>");
            response.setHeader("refresh", "0;url=user_login.jsp");
        }
        else if(user.getName() == null||"".equals(user.getName())){
        	//用户名不存在
        	out.print("<script language='javaScript'> alert('用户名不存在');</script>");
            response.setHeader("refresh", "0;url=user_login.jsp");
        }
        else if(user.getPassword() == null||"".equals(user.getPassword())){
        	//密码错误
        	out.print("<script language='javaScript'> alert('密码错误');</script>");
            response.setHeader("refresh", "0;url=user_login.jsp");
        }
        else{
        	//登录成功
        	//存储数据
        	request.setAttribute("user", user);
        	//转发
        	request.getRequestDispatcher("/successLogin").forward(request, response);
        }
        
	}

}
