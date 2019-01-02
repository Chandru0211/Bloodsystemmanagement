package com.devops.bbm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static Connection con=null;
	
	private static String jdbc_url="jdbc:mysql://localhost:3306/bloodbank";
	private static String db_username="root";
	private static String db_password="devops";
	private static String class_name="com.mysql.cj.jdbc.Driver";
	

	public static Connection getConnection() {
		try {
			Class.forName(class_name);
			con=DriverManager.getConnection(jdbc_url, db_username, db_password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//Driver class
		public static void main (String args[]) {
			System.out.println(getConnection());
		}

}
