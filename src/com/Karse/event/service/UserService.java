package com.Karse.event.service;

import com.Karse.event.entity.Page;
import com.Karse.event.entity.User;
import com.Karse.event.entity.UserInfo;

/**
 * 
 * @author Karse
 *
 */
public interface UserService {

	/**
	 * �����˺���Ϣ
	 * @param user
	 * @return
	 */
	User login(User user);

	/**
	 * ����Email
	 * @param email
	 * @return
	 */
	String findEmail(String email);

	/**
	 * �����û���Ϣ
	 * @param user
	 * @param userInfo
	 * @return
	 */
	boolean addUser(User user, UserInfo userInfo);
	
	/**
	 * ��ȡ�������Ϊlength������
	 * @param length
	 * @return
	 */
	String getCheckCode(int length);

	/**
	 * ��ҳ��������
	 * @param currentPage
	 * @param rows
	 * @return
	 */
    Page findUserByPage(String currentPage, String rows);
}
