package com.Karse.event.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Karse.event.service.UserService;
import com.Karse.event.service.impl.UserServiceImpl;
import com.Karse.event.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static UserService userService = new UserServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmailServlet() {
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
		//获取邮箱
		String email = request.getParameter("email");
		System.out.println("获取到的邮箱"+email);
		
        //获取4个随机验证码
        String checkImageCode = userService.getCheckCode(4);
		System.out.println("随机验证码"+checkImageCode);
        
        //发送邮件
        try {
			MailUtils.sendMail(email, "LOL转会系统注册验证码", checkImageCode);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//将验证码放入HttpSession中
		HttpSession httpSession =  request.getSession();
		httpSession.setAttribute("checkImageCode",checkImageCode);
	}

}
