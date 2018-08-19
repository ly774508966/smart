package com.rabbit.smart.util.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;

/**
 * MD5 签名
 */
public class MD5Util {
    private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);

    /**
     * 获取字符串的MD5值，字符串长度32
     *
     * @param message 原始串
     * @return 签名
     */
    public static String get32(String message) {
        try {
            // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 2 将消息变成byte数组
            byte[] input = message.getBytes();
            // 3 计算后获得字节数组,这就是那128位了
            byte[] buff = md.digest(input);
            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
            return bytesToHex(buff);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 获取字符串的MD5值，字符串长度16
     *
     * @param message 原始串
     * @return 签名
     */
    public static String get16(String message) {
        String result = get32(message);
        if (StringUtils.isEmpty(result))
            return null;
        return result.substring(8, 24);
    }

    /**
     * 2进制转16进制
     *
     * @param bytes
     * @return
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        // 把数组每一字节换成16进制连成md5字符串
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];
            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }
}
