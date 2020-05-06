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
		//设置编码
//		request.setCharacterEncoding("utf-8");
		
		//验证校验
		String check = request.getParameter("checkImageCode");
		//从sesion中获取验证码
		HttpSession session = request.getSession();
		String checkCode = (String)session.getAttribute("checkImageCode");
		
		//保证验证码只能使用一次
		session.removeAttribute("checkImageCode");
		
		//比较
		if(!checkCode.equals(check)){
			//验证码错误
			java.util.Map<String, Object> map = new HashMap<String,Object>();
			map.put("flag", false);
			map.put("msg", "验证码错误");
			
			//将map对象转换成json,并传递给客户端	
	 		ObjectMapper mapper = new ObjectMapper();
			response.setContentType("application/json;charset=utf-8");
	 		mapper.writeValue(response.getWriter(), map);
			return;
		}
        
        //包装成实体类
        String userEmail = request.getParameter("email");
        String password = request.getParameter("password");
        User input = new User();
        input.setEmail(userEmail);
        input.setPassword(password); 
        
        //调用service查询
        User user = userService.login(input);       
        
        java.util.Map<String, Object> map = new HashMap<String,Object>();
        
         if(user == null){
        	//实体类不存在
        	map.put("flag", false);
         	map.put("msg", "邮箱或密码错误");
        }
        else{
        	//登录成功      	
        	map.put("flag", true);
         	map.put("msg", "登录成功");
         	map.put("role", user.getRole());
         	
        }	
         
     	//将map对象转换成json,并传递给客户端	
 		ObjectMapper mapper = new ObjectMapper();
 				
 		//将数值返回客户端
 		//设置类型
 		response.setContentType("application/json;charset=utf-8");
 		mapper.writeValue(response.getWriter(), map);
    		
        
	}

}
