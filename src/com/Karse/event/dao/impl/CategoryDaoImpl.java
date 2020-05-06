package com.Karse.event.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.Karse.event.dao.CategoryDao;
import com.Karse.event.entity.Category;
import com.Karse.event.util.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao{

	
	@Override
	public List<Category> findAll() {
		
		String sql = "select * from t_category";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();	
			List<Category> list = new ArrayList<>();
			
			if(rs.next()){
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				list.add(category);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
