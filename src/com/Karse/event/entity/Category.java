package com.Karse.event.entity;
/**
 * 主页目录实体类
 * @author Karse
 *
 */
public class Category {
	
	/**
	 * name:目录名字
	 */
	
	private int id;
	private String name;
	
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
}
