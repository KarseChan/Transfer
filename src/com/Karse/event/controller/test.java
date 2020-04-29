package com.Karse.event.controller;

import com.Karse.event.dao.impl.UserDaoImpl;
import com.Karse.event.entity.User;

public class test {
	
	public void test(){
		User user = new User();
		user.setName("admin");
		user.setPassword("123");
		
		UserDaoImpl dao = new UserDaoImpl();
		User u = dao.login(user);
		System.out.println(u);
	}
}
