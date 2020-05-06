package com.Karse.event.entity;
/**
 * 用户实体
 * @author Karse
 *
 */
public class User {
	
	/**
	 * id-编号
	 * email-邮箱
	 * password-密码
	 * role-权限
	 */
	
	private int id;  
	private String email;  
	private String password; 
	private int role; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
}
