package com.Karse.event.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Karse.event.dao.UserDao;
import com.Karse.event.entity.User;
import com.Karse.event.util.JDBCUtils;

/**
 * 封装jdbc用户账号信息增删改查数据库的方法
 * @author Karse
 *
 */
public class UserDaoImpl implements UserDao{
	
	
	
	/**
	 * 登录方法
	 */
	@Override
	public User login(User loginUser) {		
		
		User resultUser = null; //用于返回
		String sql = "select * from t_user where name=? and password=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, loginUser.getName());
			ps.setString(2, loginUser.getPassword());
			rs = ps.executeQuery();
			//判断是否有有数据
			if(rs.next()){
				resultUser = new User();
				resultUser.setId(rs.getInt("id"));
				resultUser.setName(rs.getString("name"));;
				resultUser.setPassword(rs.getString("password"));;
				resultUser.setRole(rs.getInt("role"));
			}
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
		
		
		return resultUser;
	}
}
