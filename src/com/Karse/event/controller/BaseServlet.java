package com.Karse.event.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//完成方法分发
		//1.获取请求路径
		String uri = req.getRequestURI();
		
		//2.获取方法名称
		String methodName = uri.substring(uri.lastIndexOf('/')+1);
		
		//3.获取方法对象Method
		try {
			//获取方法
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//4.执行方法
			try {
				method.invoke(this, req,resp);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	/**
	 * 直接将传入的对象序列化为json,并且写回客户端
	 * @param obj
	 */
	public void writeValue(Object obj, HttpServletResponse response) throws IOException{
		//将map对象转换成json,并传递给客户端
		ObjectMapper mapper = new ObjectMapper();

		//将数值返回客户端
		//设置类型
		response.setContentType("application/json;charset=utf-8");
		mapper.writeValue(response.getWriter(), obj);
	}


	/**
	 * 将传入的对象序列化为json，返回
	 * @param obj
	 * @return
	 */
	public String writeValueAsString(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

}
