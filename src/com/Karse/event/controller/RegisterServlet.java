package com.Karse.event.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Karse.event.entity.User;
import com.Karse.event.entity.UserInfo;
import com.Karse.event.service.UserService;
import com.Karse.event.service.impl.UserServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.������ݲ���װ
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String message = request.getParameter("message");
		String lastTeam = request.getParameter("lastTeam");
		String time = request.getParameter("date");

		//��������������֤��
		String inputCheck = request.getParameter("checkCode");

		//��sesion�л�ȡ�����������֤��
		HttpSession session = request.getSession();
		String checkCode = (String)session.getAttribute("checkImageCode");
		
		User user = new User();
		UserInfo userInfo = new UserInfo();
		
		user.setEmail(email);
		user.setPassword(password);
		userInfo.setName(name);
		userInfo.setAge(age);
		userInfo.setMessage(message);
		userInfo.setLastTeam(lastTeam);
		
		
		//2.����service��
		boolean register = userService.addUser(user,userInfo);
		
		//������������Ӧ�ص����ݸ�ʽ��{"emailExit":true,"msg":"�������ѱ���,�����һ��"}
		//					      {"emailExit":false,"msg":"������� "}
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		//�Ƚ�
		if(!inputCheck.equals(checkCode)){
			//��֤�����
			map = new HashMap<String,Object>();
			map.put("flag", false);
			map.put("msg", "��֤�����");
			
			//��map����ת����json,�����ݸ��ͻ���	
	 		ObjectMapper mapper = new ObjectMapper();
			response.setContentType("application/json;charset=utf-8");
	 		mapper.writeValue(response.getWriter(), map);
			return;
		}
		else if(register){
		   	//ע��ɹ�
		    map.put("flag", true);
		    map.put("msg", "ע��ɹ�");
        }
        else{
        	//ע��ʧ��
        	map.put("flag", false);
        	map.put("msg", "ע��ʧ��,�����Ѵ���");
        }
		
		//��map����ת����json,�����ݸ��ͻ���		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
				
		//����ֵ���ؿͻ���
		//��������
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}

}
