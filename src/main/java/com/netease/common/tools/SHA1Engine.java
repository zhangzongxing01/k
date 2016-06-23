package com.netease.common.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;


public class SHA1Engine {
    public static String encrypt(String plainText) {

        String pwd = "";
        if (StringUtils.isNotBlank(plainText)) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA1");
                md.update(plainText.getBytes());
                

                byte[] pwdByte = md.digest();
                //使用大数类型会抹去密文开头零15.10.17
//                pwd = new BigInteger(1, i).toString(16);
                
                // 首先初始化一个字符数组，用来存放每个16进制字符
                char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
                char[] resultCharArray =new char[pwdByte.length * 2];
                // 遍历字节数组，转换成字符
                int index = 0;
                for (byte b : pwdByte) {
                   resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
                   resultCharArray[index++] = hexDigits[b& 0xf];
                }
                pwd = new String(resultCharArray).toLowerCase();
            } catch (NoSuchAlgorithmException e) {
               return "";
            }
        }
        return pwd;
    }
}
