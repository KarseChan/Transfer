package com.Karse.event.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.Karse.event.dao.UserDao;
import com.Karse.event.entity.User;
import com.Karse.event.entity.UserInfo;
import com.Karse.event.util.JDBCUtils;

/**
 * 封装jdbc用户账号信息增删改查数据库的方法
 * @author Karse
 *
 */
public class UserDaoImpl implements UserDao{
	
	
	
	/**
	 * 返回用户实体类
	 */
	@Override
	public User selectUser(User loginUser) {		
		
		User resultUser = null; //用于返回
		String sql = "select * from t_user where email=? and password=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);ps.setString(1, loginUser.getEmail());
			ps.setString(2, loginUser.getPassword());
			rs = ps.executeQuery();	
			
			//判断是否有有数据
			if(rs.next()){
				resultUser = new User();
				resultUser.setId(rs.getInt("id"));
				resultUser.setEmail(rs.getString("email"));
				resultUser.setPassword(rs.getString("password"));
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
	
	
	/**
	 * 判断邮箱是否存在
	 */
	@Override
	public String selectEmail(String email) {
		
		String selectEmail = null;
		String sql = "select * from t_user where email=?";		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);		
			ps.setString(1,email);
			rs = ps.executeQuery();
			if(rs.next()){
				//存在该邮箱
				selectEmail = rs.getString("email");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
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
		return selectEmail;
	}
	
	
	/**
	 * 保存注册数据
	 */
	@Override
	public boolean addUser(User user, UserInfo userInfo) {
		
		System.out.println(userInfo.getTime());
		boolean flag = false;
		String userSql = "insert into t_user(id,t_user.`email`,t_user.`password`) values(null,?,?)";
		String infoSql = "insert into t_userinfo(id,name,age,message,lastTeam,time,userId) values(null,?,?,?,?,?,(SELECT LAST_INSERT_ID()))";
		Connection conn = null;
		PreparedStatement userPs = null;
		PreparedStatement infoPs = null;

		try {
			conn = JDBCUtils.getConnection();
			userPs = conn.prepareStatement(userSql);
			infoPs = conn.prepareStatement(infoSql);
			userPs.setString(1, user.getEmail());
			userPs.setString(2, user.getPassword());
			infoPs.setString(1, userInfo.getName());
			infoPs.setInt(2, userInfo.getAge());
			infoPs.setString(3, userInfo.getMessage());
			infoPs.setString(4, userInfo.getLastTeam());
			infoPs.setDate(5, userInfo.getTime());
			//得到数据库改变数
			int userCount = userPs.executeUpdate();
			int infoCount = infoPs.executeUpdate();
			
			if(userCount == 1 && infoCount == 1){
				flag = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(infoPs != null){
				try {
					infoPs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(userPs != null){
				try {
					userPs.close();
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
		
		return flag;
	}


	@Override
	public int findTotalCount() {

		int totalCount = 0;
		String sql = "select * from t_userinfo";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				totalCount += 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return totalCount;
	}


	@Override
	public List findByPage(int count, int rows) {
		List list = null;
		String sql = "select * from t_userinfo limit ? , ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1,count);
			ps.setInt(2,rows);
			rs = ps.executeQuery();
			if(rs.next()){
				UserInfo userInfo = new UserInfo();
				userInfo.setName(rs.getString("name"));
				userInfo.setAge(rs.getInt("age"));
				userInfo.setTime(rs.getDate("time"));
				userInfo.setLastTeam(rs.getString("lastTeam"));
				userInfo.setHistory(rs.getString("history"));
				userInfo.setMessage(rs.getString("message"));
				list.add(userInfo);

			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
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
