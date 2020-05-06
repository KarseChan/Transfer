package com.Karse.event.service.impl;

import java.util.List;
import java.util.Random;

import com.Karse.event.dao.UserDao;
import com.Karse.event.dao.impl.UserDaoImpl;
import com.Karse.event.entity.Page;
import com.Karse.event.entity.User;
import com.Karse.event.entity.UserInfo;
import com.Karse.event.service.UserService;

public class UserServiceImpl implements UserService{
	
	private static UserDao userDao = new UserDaoImpl();
	
	@Override
	public User login(User user) {
		if(user == null){
			System.out.println("请求参数有误");
		}
		//从数据库中查找记录
		User loginUser = userDao.selectUser(user);
		return loginUser;
	}
	
	@Override
	public String findEmail(String email) {
		String findEmail = userDao.selectEmail(email);
		return findEmail;
	}
	
	@Override
	public boolean addUser(User user, UserInfo userInfo) {
		String selectEmail = userDao.selectEmail(user.getEmail());
		if(selectEmail != null){
			//邮箱存在，注册失败
			return false;
		}
		else{
			//保存用户信息
			boolean addUser = userDao.addUser(user,userInfo);
			return addUser;
		}
	}
	
	@Override
	public String getCheckCode(int length) {
		
		String base = "0123456789";		
		int size = base.length();		
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		
		for(int i = 1; i<=length; i++){
			//产生0到size的随机值
			int index = r.nextInt(size);
			//在base字符串中获取下标为Index的字符
			char c = base.charAt(index);
			//将c放入到StringBuffer中
			sb.append(c);
		}
		return sb.toString();
	}

	@Override
	public Page findUserByPage(String _currentPage, String _rows) {

		int currentPage = Integer.parseInt(_currentPage);
		int rows = Integer.parseInt(_rows);

		//1.创建空的Page对象
		Page p = new Page();

		//2.设置参数
		p.setCurrentPage(currentPage);
		p.setRows(rows);

		//3.调用dao查询总记录数
		int totalCount = userDao.findTotalCount();
		p.setTotalCount(totalCount);
		//4.调用dao查询list集合
		//计算开始的记录的索引
		int count = (currentPage - 1) * rows;
		List list = userDao.findByPage(count, rows);
		p.setList(list);

		//5.计算总页码
		int totalPage = totalCount % rows == 0 ? totalCount / rows : (totalCount / rows) + 1;
		p.setTotalPage(totalPage);

	}
}
