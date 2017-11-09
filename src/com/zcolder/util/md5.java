package com.zcolder.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * �����md5������Ҫ���ģ���web���������У�������Ϊ�����ĸ��Ķ����±������·��
 * �ᷢ��NoClassDefFoundError����ע�⣬���ﲢ�����쳣��
 * @author �ų���
 *
 */
public class md5 {
	/**
	 * ȫ������
	 */
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5","6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    
    /**
     * @param bByte ��Ź�ϣֵ�����byte����
     * @return  ������ʽΪ���ָ��ַ���
     */
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }
    
    /**
     * @param bByte  ��Ź�ϣֵ�����byte����
     * @return ����ת���ֽ�����Ϊ16�����ִ�
     */
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }
    
    public static String GetMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() �ú�������ֵΪ��Ź�ϣֵ�����byte����
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
}
