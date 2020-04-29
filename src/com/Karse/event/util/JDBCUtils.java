package com.Karse.event.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * JDBC工具类 
 * @author Karse
 *
 */
public class JDBCUtils {

	private static String url = "jdbc:mysql://localhost:3306/transfer system";
	private static String user = "root";
	private static String password = "123456";
	
	
	/**
	 * JDBC连接数据库
	 * @throws ClassNotFoundException 
	 */	
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		/**
		 * 得到连接接口的方法
		 */
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url,user,password);
		return conn;
	}
}
