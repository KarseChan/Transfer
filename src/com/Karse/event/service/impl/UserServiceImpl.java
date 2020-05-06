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
			System.out.println("�����������");
		}
		//�����ݿ��в��Ҽ�¼
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
			//������ڣ�ע��ʧ��
			return false;
		}
		else{
			//�����û���Ϣ
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
			//����0��size�����ֵ
			int index = r.nextInt(size);
			//��base�ַ����л�ȡ�±�ΪIndex���ַ�
			char c = base.charAt(index);
			//��c���뵽StringBuffer��
			sb.append(c);
		}
		return sb.toString();
	}

	@Override
	public Page findUserByPage(String _currentPage, String _rows) {

		int currentPage = Integer.parseInt(_currentPage);
		int rows = Integer.parseInt(_rows);

		//1.�����յ�Page����
		Page p = new Page();

		//2.���ò���
		p.setCurrentPage(currentPage);
		p.setRows(rows);

		//3.����dao��ѯ�ܼ�¼��
		int totalCount = userDao.findTotalCount();
		p.setTotalCount(totalCount);
		//4.����dao��ѯlist����
		//���㿪ʼ�ļ�¼������
		int count = (currentPage - 1) * rows;
		List list = userDao.findByPage(count, rows);
		p.setList(list);

		//5.������ҳ��
		int totalPage = totalCount % rows == 0 ? totalCount / rows : (totalCount / rows) + 1;
		p.setTotalPage(totalPage);

	}
}
