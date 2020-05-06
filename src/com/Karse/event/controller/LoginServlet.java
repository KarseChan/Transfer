package com.Karse.event.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Karse.event.dao.impl.UserDaoImpl;
import com.Karse.event.entity.User;
import com.Karse.event.service.UserService;
import com.Karse.event.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	 		ObjectMapper mapper = new ObjectMapper();
			response.setContentType("application/json;charset=utf-8");
	 		mapper.writeValue(response.getWriter(), map);
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
 		ObjectMapper mapper = new ObjectMapper();
 				
 		//����ֵ���ؿͻ���
 		//��������
 		response.setContentType("application/json;charset=utf-8");
 		mapper.writeValue(response.getWriter(), map);
    		
        
	}

}
