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
		//1.获得数据并包装
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String message = request.getParameter("message");
		String lastTeam = request.getParameter("lastTeam");
		String time = request.getParameter("date");

		//获得输入进来的验证码
		String inputCheck = request.getParameter("checkCode");

		//从sesion中获取发送邮箱的验证码
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
		
		
		//2.调用service层
		boolean register = userService.addUser(user,userInfo);
		
		//期望服务器响应回的数据格式：{"emailExit":true,"msg":"此邮箱已被绑定,请更换一个"}
		//					      {"emailExit":false,"msg":"邮箱可用 "}
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		//比较
		if(!inputCheck.equals(checkCode)){
			//验证码错误
			map = new HashMap<String,Object>();
			map.put("flag", false);
			map.put("msg", "验证码错误");
			
			//将map对象转换成json,并传递给客户端	
	 		ObjectMapper mapper = new ObjectMapper();
			response.setContentType("application/json;charset=utf-8");
	 		mapper.writeValue(response.getWriter(), map);
			return;
		}
		else if(register){
		   	//注册成功
		    map.put("flag", true);
		    map.put("msg", "注册成功");
        }
        else{
        	//注册失败
        	map.put("flag", false);
        	map.put("msg", "注册失败,邮箱已存在");
        }
		
		//将map对象转换成json,并传递给客户端		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
				
		//将数值返回客户端
		//设置类型
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}

}
