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
	 * 登录方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			writeValue(map,response);

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
	
	/**
	 * 登录方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
			writeValue(map,response);

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
		writeValue(map,response);
 		
	}
	
	/**
	 * 发送邮件
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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


	public void findUserByPage(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		//当前页码和一页放的记录数
		String currentPage = request.getParameter("currentPage");
		String rows = request.getParameter("rows");

		//查询
		Page p = userService.findUserByPage(currentPage,rows);

		//将Page 存入request
		request.setAttribute("p",p);
		//转发到list.jsp
		request.getRequestDispatcher("/list.jsp").forward(request,response);
	}
}
