package com.rabbit.smart.util.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Base64
 * Created by xuzhang on 2017/8/27.
 */
public class Base64Util {
    /**
     * Base64加密
     *
     * @param content 原始数据
     * @return 加密结果
     * @throws Exception 加密异常
     */
    public static String encryptBASE64(byte[] content) throws Exception {
        String encode = (new BASE64Encoder()).encodeBuffer(content);
        return encode.replaceAll("\r\n", "");
    }

    /**
     * Base64解密
     *
     * @param content 解密数据
     * @return 解密结果
     * @throws Exception 解密异常
     */
    public static byte[] decryptBASE64(String content) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(content);
    }
}
