package com.Karse.event.dao;

import java.util.List;

import com.Karse.event.entity.Category;;

public interface CategoryDao {
	
	/**
	 * ²éÑ¯ËùÓĞ
	 * @return
	 */
	public List<Category> findAll();
}
