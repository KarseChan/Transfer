package com.Karse.event.dao;

import com.Karse.event.entity.User;
import com.Karse.event.entity.UserInfo;

import java.util.List;

public interface UserDao {

	/**
	 * �����û�ʵ����
	 * @param loginUser
	 * @return
	 */
	User selectUser(User loginUser);
	
	/**
	 * ���Ҹ�����
	 * @param email
	 * @return
	 */
	String selectEmail(String email);
	
	/**
	 * �����û�
	 * @param user
	 * @param userInfo
	 * @return
	 */
	boolean addUser(User user, UserInfo userInfo);

    int findTotalCount();

	List findByPage(int count, int rows);
}
