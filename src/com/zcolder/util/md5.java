package com.zcolder.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 这里的md5类名不要更改，在web容器配置中，不会因为类名的更改而重新变更加载路径
 * 会发生NoClassDefFoundError错误，注意，这里并不是异常！
 * @author 张朝锋
 *
 */
public class md5 {
	/**
	 * 全局数组
	 */
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5","6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    
    /**
     * @param bByte 存放哈希值结果的byte数组
     * @return  返回形式为数字跟字符串
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
     * @param bByte  存放哈希值结果的byte数组
     * @return 返回转换字节数组为16进制字串
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
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
}
