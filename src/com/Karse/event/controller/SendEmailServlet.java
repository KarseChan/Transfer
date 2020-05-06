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
		//��ȡ����
		String email = request.getParameter("email");
		System.out.println("��ȡ��������"+email);
		
        //��ȡ4�������֤��
        String checkImageCode = userService.getCheckCode(4);
		System.out.println("�����֤��"+checkImageCode);
        
        //�����ʼ�
        try {
			MailUtils.sendMail(email, "LOLת��ϵͳע����֤��", checkImageCode);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//����֤�����HttpSession��
		HttpSession httpSession =  request.getSession();
		httpSession.setAttribute("checkImageCode",checkImageCode);
	}

}
