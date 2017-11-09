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
 * �û�ע����
 * @author �ų���
 */
public class RegisteredDao {
	/**
	 * �û�ע��
	 * @param UserRegisteredInfo �û�ע��������Ϣ
	 * @return
	 */
	public static String addUser(String[] UserRegisteredInfo) {
		String Flag = "ע��ɹ�";
		int usernameFlag = RegisteredDao.checkUserName(UserRegisteredInfo[1]);
		
		//��������ʽ
		if(!RegisteredDao.checkEmail(UserRegisteredInfo[0])) {
			return Flag = "�����ʽ��������д��ȷ������!";
		}
		//����û����Ϸ�
		if(usernameFlag == 1) {
			return Flag = "�û����Ѿ����ڣ�";
		}else if(usernameFlag == 2) {
			return Flag = "�û������̣������볬��4����Ӣ����ĸ����";
		}else if(usernameFlag == 3) {
			return Flag = "�û��������������벻����30����Ӣ����ĸ����";
		}else if(usernameFlag == 4) {
			return Flag = "δ֪���󣬷����쳣��";
		}else if(usernameFlag == 5){
			return Flag = "�û������ʧ��";
		}
		//��������Ƿ�һ��
		if(!UserRegisteredInfo[2].equals(UserRegisteredInfo[3])) {
			return Flag = "�������벻һ�£����������룡";
		}
		
		//������������
		Connection conn = null;
		//Ԥ����sql��������
		PreparedStatement pstmt = null;
		//�������������
		int rs = 0;
		
		try {
			String password = md5.GetMD5Code(UserRegisteredInfo[2]);
			conn = DBHelper.getConnection();
			String addUserSql = "insert users(username,password,useremail) values (\""+UserRegisteredInfo[1]+"\",\""+password+"\",\""+UserRegisteredInfo[0]+"\");";
			pstmt = conn.prepareStatement(addUserSql);
			rs = pstmt.executeUpdate();
			if(rs != 1) {
				Flag = "ע��ʧ�ܣ�����δ֪";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Flag = "δ֪�������쳣��";
		}finally {
			//�ͷ����ݼ�����
//			if(rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
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
		
		return Flag;
	}
	/**
	 * ������ʽ��֤�����ʽ����
	 * @param email ����
	 * @return true ����Ϸ�|false ���䲻�Ϸ�
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
	 * ����û����Ƿ�Ϸ�
	 * 1������û����Ƿ��ظ�
	 * 2������û�������
	 * @param username �û���
	 * @param checkFlag �û���״̬  ������
	 * 0 һ������ 
	 * 1 �û��Ѿ�����
	 * 2 �û������ȹ��� 
	 * 3 �û������ȹ��� 
	 * 4 δ֪���󣬳������쳣
	 * 5 �û������ʧ��
	 * @return
	 */
	public static int checkUserName(String username) {
		//�û������״̬
		int checkFlag = 5;
		//������������
		Connection conn = null;
		//Ԥ����sql��������
		PreparedStatement pstmt = null;
		//�������������
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
		return checkFlag;
	}
}
