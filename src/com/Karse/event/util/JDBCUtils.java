package com.Karse.event.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * JDBC������ 
 * @author Karse
 *
 */
public class JDBCUtils {

	private static String url = "jdbc:mysql://localhost:3306/transfer system";
	private static String user = "root";
	private static String password = "123456";
	
	
	/**
	 * JDBC�������ݿ�
	 * @throws ClassNotFoundException 
	 */	
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		/**
		 * �õ����ӽӿڵķ���
		 */
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url,user,password);
		return conn;
	}
}
