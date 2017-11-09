package com.zcolder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zcolder.util.DBHelper;
import com.zcolder.util.md5;

/**
 * 验证账户和密码是否正确
 * @author 张朝锋
 *
 */
public class Validation {
	/**
	 * 验证账号密码是否正确
	 * @param username 用户名
	 * @param password 用户密码
	 * @throws Exception 捕获异常，没有向上抛出
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
					return "用户密码正确";
				}else {
					return "用户密码错误";
				}
			}
			return message;
		}catch(Exception e) {
			e.printStackTrace();
			return message;
		}finally {
			//释放数据集对象
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//释放语句对象
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
