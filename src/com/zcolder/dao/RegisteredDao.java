package com.zcolder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zcolder.util.DBHelper;
import com.zcolder.util.md5;

/**
 * 用户注册类
 * @author 张朝锋
 */
public class RegisteredDao {
	/**
	 * 用户注册
	 * @param UserRegisteredInfo 用户注册所填信息
	 * @return
	 */
	public static String addUser(String[] UserRegisteredInfo) {
		String Flag = "注册成功";
		int usernameFlag = RegisteredDao.checkUserName(UserRegisteredInfo[1]);
		
		//检查邮箱格式
		if(!RegisteredDao.checkEmail(UserRegisteredInfo[0])) {
			return Flag = "邮箱格式错误，请填写正确的邮箱!";
		}
		//检查用户名合法
		if(usernameFlag == 1) {
			return Flag = "用户名已经存在！";
		}else if(usernameFlag == 2) {
			return Flag = "用户名过短，请输入超过4个的英文字母或汉字";
		}else if(usernameFlag == 3) {
			return Flag = "用户名过长，请输入不超过30个的英文字母或汉字";
		}else if(usernameFlag == 4) {
			return Flag = "未知错误，发生异常！";
		}else if(usernameFlag == 5){
			return Flag = "用户名检查失败";
		}
		//检查密码是否一致
		if(!UserRegisteredInfo[2].equals(UserRegisteredInfo[3])) {
			return Flag = "两次密码不一致，请重新输入！";
		}
		
		//驱动对象引用
		Connection conn = null;
		//预编译sql对象引用
		PreparedStatement pstmt = null;
		//结果集对象引用
		int rs = 0;
		
		try {
			String password = md5.GetMD5Code(UserRegisteredInfo[2]);
			conn = DBHelper.getConnection();
			String addUserSql = "insert users(username,password,useremail) values (\""+UserRegisteredInfo[1]+"\",\""+password+"\",\""+UserRegisteredInfo[0]+"\");";
			pstmt = conn.prepareStatement(addUserSql);
			rs = pstmt.executeUpdate();
			if(rs != 1) {
				Flag = "注册失败，错误未知";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Flag = "未知错误发生异常！";
		}finally {
			//释放数据集对象
//			if(rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
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
		
		return Flag;
	}
	/**
	 * 正则表达式验证邮箱格式函数
	 * @param email 邮箱
	 * @return true 邮箱合法|false 邮箱不合法
	 */
	public static boolean checkEmail(String email){
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	/**
	 * 检查用户名是否合法
	 * 1、检查用户名是否重复
	 * 2、检查用户名长度
	 * @param username 用户名
	 * @param checkFlag 用户名状态  参数：
	 * 0 一切正常 
	 * 1 用户已经存在
	 * 2 用户名长度过短 
	 * 3 用户名长度过长 
	 * 4 未知错误，程序发生异常
	 * 5 用户名检测失败
	 * @return
	 */
	public static int checkUserName(String username) {
		//用户名检查状态
		int checkFlag = 5;
		//驱动对象引用
		Connection conn = null;
		//预编译sql对象引用
		PreparedStatement pstmt = null;
		//结果集对象引用
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			String isExistSql = "SELECT username FROM users WHERE username=\""+username+"\" LIMIT 1";
			pstmt = conn.prepareStatement(isExistSql);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				char[] usernameNum = username.toCharArray();
				if(usernameNum.length < 4) {
					checkFlag = 2;
				}else if(usernameNum.length > 30) {
					checkFlag = 3;
				}else {
					checkFlag = 0;
				}
			}else {
				checkFlag = 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			checkFlag =4;
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
		return checkFlag;
	}
}
