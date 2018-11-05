package aaa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil{
	static String user ="db170104130112";
	static String password="170104130112";
	static String url = "jdbc:mysql://www.mycourse.top:3306/user?characterEncoding=UTF-8";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			
		}
	}
		public static Connection getConnection() {
			Connection connection = null;
			try {
				connection = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		}
		
		
		
		
	}
	
