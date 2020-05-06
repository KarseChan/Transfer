package com.Karse.event.service.impl;

import java.util.List;

import com.Karse.event.dao.CategoryDao;
import com.Karse.event.dao.impl.CategoryDaoImpl;
import com.Karse.event.entity.Category;
import com.Karse.event.entity.User;
import com.Karse.event.service.CategoryService;

public class CategoryServiceImpl implements CategoryService{
	
	private static CategoryDao categoryDao = new CategoryDaoImpl();
	
	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
}
