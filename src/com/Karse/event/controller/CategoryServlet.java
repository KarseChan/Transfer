package com.Karse.event.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Karse.event.entity.Category;
import com.Karse.event.service.CategoryService;
import com.Karse.event.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/categoryServlet/*")
public class CategoryServlet extends BaseServlet {
	
	private CategoryService categoryservice = new CategoryServiceImpl();
	
    protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//1.调用Service 查询所有
    	List<Category> cs = categoryservice.findAll();
    	//2.序列化json返回
		writeValue(cs,response);
    }   


}
