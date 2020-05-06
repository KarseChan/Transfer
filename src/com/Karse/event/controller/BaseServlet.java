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
		//��ɷ����ַ�
		//1.��ȡ����·��
		String uri = req.getRequestURI();
		
		//2.��ȡ��������
		String methodName = uri.substring(uri.lastIndexOf('/')+1);
		
		//3.��ȡ��������Method
		try {
			//��ȡ����
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//4.ִ�з���
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
	 * ֱ�ӽ�����Ķ������л�Ϊjson,����д�ؿͻ���
	 * @param obj
	 */
	public void writeValue(Object obj, HttpServletResponse response) throws IOException{
		//��map����ת����json,�����ݸ��ͻ���
		ObjectMapper mapper = new ObjectMapper();

		//����ֵ���ؿͻ���
		//��������
		response.setContentType("application/json;charset=utf-8");
		mapper.writeValue(response.getWriter(), obj);
	}


	/**
	 * ������Ķ������л�Ϊjson������
	 * @param obj
	 * @return
	 */
	public String writeValueAsString(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

}
