package com.Karse.event.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
/**
 * 用户信息
 * @author Karse
 *
 */
public class UserInfo {
	

	
	private int id;
	private String name;
	private int age;

	private String message;
	private String lastTeam;

	//日期格式化
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date time;
	private String history;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLastTeam() {
		return lastTeam;
	}
	public void setLastTeam(String lastTeam) {
		this.lastTeam = lastTeam;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	private int userId;
}
