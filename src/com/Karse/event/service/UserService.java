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
	 * 返回账号信息
	 * @param user
	 * @return
	 */
	User login(User user);

	/**
	 * 查找Email
	 * @param email
	 * @return
	 */
	String findEmail(String email);

	/**
	 * 增加用户信息
	 * @param user
	 * @param userInfo
	 * @return
	 */
	boolean addUser(User user, UserInfo userInfo);
	
	/**
	 * 获取随机个数为length的数字
	 * @param length
	 * @return
	 */
	String getCheckCode(int length);

	/**
	 * 分页查找数据
	 * @param currentPage
	 * @param rows
	 * @return
	 */
    Page findUserByPage(String currentPage, String rows);
}
