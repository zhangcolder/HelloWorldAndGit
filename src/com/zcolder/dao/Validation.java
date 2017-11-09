package com.zcolder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zcolder.util.DBHelper;
import com.zcolder.util.md5;

/**
 * ��֤�˻��������Ƿ���ȷ
 * @author �ų���
 *
 */
public class Validation {
	/**
	 * ��֤�˺������Ƿ���ȷ
	 * @param username �û���
	 * @param password �û�����
	 * @throws Exception �����쳣��û�������׳�
	 */
	public static String checkPassword(String username,String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String pw = md5.GetMD5Code(password);
		String message = null;
		try {
			conn = DBHelper.getConnection();
			String sql = "SELECT password FROM users WHERE username=\""+username+"\" LIMIT 1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				message = rs.getString("password");
				if(message.equals(pw)) {
					return "�û�������ȷ";
				}else {
					return "�û��������";
				}
			}
			return message;
		}catch(Exception e) {
			e.printStackTrace();
			return message;
		}finally {
			//�ͷ����ݼ�����
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//�ͷ�������
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
