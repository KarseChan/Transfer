package com.Karse.event.service.impl;

import com.Karse.event.dao.UserDao;
import com.Karse.event.dao.impl.UserDaoImpl;
import com.Karse.event.entity.User;
import com.Karse.event.service.UserService;

public class UserServiceImpl implements UserService{
	
	private static UserDao userDao = new UserDaoImpl();
	
	@Override
	public User login(User user) {
		if(user == null){
			System.out.println("�����������");
		}
		//�����ݿ��в��Ҽ�¼
		User loginUser = userDao.login(user);
		return loginUser;
	}
}
