package com.rabbit.smart.util.security;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * AES 对称加解密
 * Created by xuzhang on 2017/9/9.
 */
public class AESUtil {

    private static int length = 128;

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return 加密字节数组
     * @throws Exception 加密异常
     */
    private static byte[] encrypt(String content, String password) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());
        kgen.init(length, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
        byte[] byteContent = content.getBytes("utf-8");
        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
        byte[] result = cipher.doFinal(byteContent);
        return result; // 加密
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return 解密字节数组
     * @throws Exception 解密异常
     */
    private static byte[] decrypt(byte[] content, String password) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());
        kgen.init(length, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
        byte[] result = cipher.doFinal(content);
        return result; // 加密
    }

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return 加密字符串
     * @throws Exception 加密异常
     */
    public static String encrypt2Str(String content, String password) throws Exception {
        byte[] encryptResult = encrypt(content, password);
        return Base64.encode(encryptResult);
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return 加密字符串
     * @throws Exception 解密异常
     */
    public static String decrypt2Str(String content, String password) throws Exception {
        byte[] decryptResult = decrypt(Base64.decode(content), password);
        return new String(decryptResult, "UTF-8");
    }
}
