package com.Karse.event.dao;

import java.util.List;

import com.Karse.event.entity.Category;;

public interface CategoryDao {
	
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<Category> findAll();
}
