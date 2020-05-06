package com.Karse.event.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Karse.event.entity.Page;
import com.Karse.event.entity.User;
import com.Karse.event.entity.UserInfo;
import com.Karse.event.service.UserService;
import com.Karse.event.service.impl.UserServiceImpl;
import com.Karse.event.util.MailUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
	
	private static UserService userService = new UserServiceImpl();
	
	/**
	 * ��¼����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			writeValue(map,response);

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
	
	/**
	 * ��¼����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//���ñ���
//		request.setCharacterEncoding("utf-8");

		//��֤У��
		String check = request.getParameter("checkImageCode");
		//��sesion�л�ȡ��֤��
		HttpSession session = request.getSession();
		String checkCode = (String)session.getAttribute("checkImageCode");

		//��֤��֤��ֻ��ʹ��һ��
		session.removeAttribute("checkImageCode");

		//�Ƚ�
		if(!checkCode.equals(check)){
			//��֤�����
			java.util.Map<String, Object> map = new HashMap<String,Object>();
			map.put("flag", false);
			map.put("msg", "��֤�����");

			//��map����ת����json,�����ݸ��ͻ���	
			writeValue(map,response);

			return;
		}

		//��װ��ʵ����
		String userEmail = request.getParameter("email");
		String password = request.getParameter("password");
		User input = new User();
		input.setEmail(userEmail);
		input.setPassword(password);

		//����service��ѯ
		User user = userService.login(input);

		java.util.Map<String, Object> map = new HashMap<String,Object>();

		if(user == null){
			//ʵ���಻����
			map.put("flag", false);
			map.put("msg", "������������");
		}
		else{
			//��¼�ɹ�
			map.put("flag", true);
			map.put("msg", "��¼�ɹ�");
			map.put("role", user.getRole());

		}

		//��map����ת����json,�����ݸ��ͻ���
		writeValue(map,response);
 		
	}
	
	/**
	 * �����ʼ�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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


	public void findUserByPage(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		//��ǰҳ���һҳ�ŵļ�¼��
		String currentPage = request.getParameter("currentPage");
		String rows = request.getParameter("rows");

		//��ѯ
		Page p = userService.findUserByPage(currentPage,rows);

		//��Page ����request
		request.setAttribute("p",p);
		//ת����list.jsp
		request.getRequestDispatcher("/list.jsp").forward(request,response);
	}
}
