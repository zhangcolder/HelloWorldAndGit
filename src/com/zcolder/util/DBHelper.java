package com.zcolder.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
	/**
	 * 数据库驱动名
	 */
	private static final String driver = "com.mysql.jdbc.Driver";
	/**
	 * 数据库地址
	 */
	private static final String url = "jdbc:mysql://localhost:3306/javawebdemo";
	/**
	 * 数据库管理者用户名
	 */
	private static final String username = "root";
	/**
	 * 数据库管理者密码
	 */
	private static final String password = "1234";
	
	private static Connection conn = null;
	
	//静态代码块负责加载驱动
		static 
		{
			try {
				Class.forName(driver);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		//单例模式返回数据库连接对象
		public static Connection getConnection() throws Exception {
			if(conn == null) {
				synchronized (DBHelper.class) {
					if(conn == null) {
						conn = DriverManager.getConnection(url, username, password);
						return conn;
					}
				}
			}
			return conn;
		}
}
