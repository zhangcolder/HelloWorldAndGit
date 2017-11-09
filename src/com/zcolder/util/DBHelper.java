package com.zcolder.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
	/**
	 * ���ݿ�������
	 */
	private static final String driver = "com.mysql.jdbc.Driver";
	/**
	 * ���ݿ��ַ
	 */
	private static final String url = "jdbc:mysql://localhost:3306/javawebdemo";
	/**
	 * ���ݿ�������û���
	 */
	private static final String username = "root";
	/**
	 * ���ݿ����������
	 */
	private static final String password = "1234";
	
	private static Connection conn = null;
	
	//��̬����鸺���������
		static 
		{
			try {
				Class.forName(driver);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		//����ģʽ�������ݿ����Ӷ���
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
