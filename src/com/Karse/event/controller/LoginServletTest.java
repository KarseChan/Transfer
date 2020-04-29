package com.Karse.event.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServletTest
 */
@WebServlet("/LoginServletTest")
public class LoginServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServletTest() {
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
	       String name = request.getParameter("name");
	        String password = request.getParameter("password");
	  
	        String html = null;
	        
	        if ("admin".equals(name) && "123".equals(password)){
	            html = "<div style='color:green'>µÇÂ¼³É¹¦</div>";
	        } 
	        else{
	            html = "<div style='color:red'>µÇÂ¼Ê§°Ü</div>";
	        }
	        
	        response.setContentType("text/html; charset=UTF-8");
	        PrintWriter pw = response.getWriter();
	        pw.println(html);
	}

}
