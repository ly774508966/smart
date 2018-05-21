package com.rabbit.smart.test;

import com.alibaba.fastjson.JSONObject;
import com.rabbit.smart.util.ConvertUtil;
import com.rabbit.smart.util.DateUtil;
import com.rabbit.smart.util.io.FileUtil;
import com.rabbit.smart.util.io.ResourceUtil;
import com.rabbit.smart.util.security.AESUtil;
import com.rabbit.smart.util.security.Base64Util;
import com.rabbit.smart.util.security.MD5Util;
import com.rabbit.smart.util.security.RSAUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xuzhang on 2018/5/21.
 */
public class UtilTest {

    @Test
    public void ArrayTest() {
        //将给定的数据添加到指定的数组中，返回一个新的数组
        String[] newArray = ArrayUtils.add(new String[]{"AB", "CD"}, "EF");
        System.out.println(JSONObject.toJSONString(newArray));

        List<String> list = ConvertUtil.arrayToList(newArray);
        System.out.println(JSONObject.toJSONString(list));

        String[] array = ConvertUtil.listToArray(list);
        System.out.println(JSONObject.toJSONString(array));
    }

    @Test
    public void StringTest() {
        //检查字符串中是否包含指定字符、任一字符
        Assert.assertEquals(StringUtils.contains("abc", "a"), true);
        Assert.assertEquals(StringUtils.contains("abc", "z"), false);
        Assert.assertEquals(StringUtils.containsAny("abcd", "ab", "cd"), true);
        Assert.assertEquals(StringUtils.containsAny("abc", "d", "abc"), true);

        //检查字符串中出现指定字符的次数
        Assert.assertEquals(StringUtils.countMatches("abba", "a"), 2);
        Assert.assertEquals(StringUtils.countMatches("abba", "ab"), 1);
        Assert.assertEquals(StringUtils.countMatches("abba", "xxx"), 0);

        //删除字符串中的空格字符
        Assert.assertEquals(StringUtils.deleteWhitespace("abc"), "abc");
        Assert.assertEquals(StringUtils.deleteWhitespace("   ab  c  "), "abc");

        //检查字符串是否为null、empty或空格字符
        Assert.assertEquals(StringUtils.isBlank(null), true);
        Assert.assertEquals(StringUtils.isBlank(""), true);
        Assert.assertEquals(StringUtils.isBlank(" "), true);
        Assert.assertEquals(StringUtils.isBlank("bob"), false);
        Assert.assertEquals(StringUtils.isBlank("  bob  "), false);
    }

    @Test
    public void RandomTest() {
        for (int i = 0; i < 100000; i++) {
            Integer number = RandomUtils.nextInt(1, 10);//[1,10)
            Assert.assertTrue(number >= 1 && number <= 9);
        }
    }

    @Test
    public void Security_AES_Test() throws Exception {
        String source = "虹湾";
        String password = "12345678";
        System.out.println("input:" + source);

        String encode = AESUtil.encrypt2Str(source, password);
        System.out.println("encode:" + encode);

        String decode = AESUtil.decrypt2Str(encode, password);
        System.out.println("decode:" + decode);

        Assert.assertEquals(source, decode);
    }

    @Test
    public void Security_Md5_Test() throws Exception {
        String source = "虹湾";
        System.out.println("input:" + source);

        String s32 = MD5Util.get32(source);
        System.out.println(s32);
        Assert.assertEquals(s32, "523e9da5a54e886ac4c50c208d7d6537".toUpperCase());

        String s16 = MD5Util.get16(source);
        System.out.println(s16);
        Assert.assertEquals(s16, "a54e886ac4c50c20".toUpperCase());
    }

    @Test
    public void Security_Base64_Test() throws Exception {
        String source = "虹湾";
        System.out.println("input:" + source);

        String base64String = Base64Util.encryptBASE64(source.getBytes());
        System.out.println("encode:" + base64String);

        String sourceString = new String(Base64Util.decryptBASE64(base64String));
        System.out.println("decode:" + sourceString);

        Assert.assertEquals(source, sourceString);
    }

    @Test
    public void Security_RSA_Test() throws Exception {
        Map<String, Object> keyMap = RSAUtil.initKey();
        String publicKey = RSAUtil.getPublicKey(keyMap);
        String privateKey = RSAUtil.getPrivateKey(keyMap);
        System.out.println("公钥 -> " + publicKey);
        System.out.println("私钥 -> " + privateKey);

        System.out.println("公钥加密，私钥解密");
        String sourceString = "虹湾";
        byte[] encodedData = RSAUtil.encrypt(sourceString.getBytes(), publicKey, true);
        byte[] decodedData = RSAUtil.decrypt(encodedData, privateKey, false);
        String targetString = new String(decodedData);
        System.out.println("加密前: " + sourceString + "，解密后: " + targetString);
        Assert.assertEquals(sourceString, targetString);

        System.out.println("私钥加密，公钥解密");
        byte[] data = sourceString.getBytes();
        encodedData = RSAUtil.encrypt(data, privateKey, false);
        decodedData = RSAUtil.decrypt(encodedData, publicKey, true);
        targetString = new String(decodedData);
        System.out.println("加密前: " + sourceString + "，解密后: " + targetString);
        Assert.assertEquals(sourceString, targetString);

        System.out.println("私钥签名，公钥验证签名");
        String sign = RSAUtil.sign(data, privateKey);
        System.out.println("签名 -> " + sign);
        boolean status = RSAUtil.verify(data, publicKey, sign);
        Assert.assertTrue(status);
    }

    @Test
    public void DateTest() throws ParseException {
        System.out.println(DateUtil.getString("yyyy-MM-dd"));
        System.out.println(DateUtil.getString("yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtil.getString("yyyy-MM-dd", new Date()));
        System.out.println(DateUtil.getString("yyyy-MM-dd HH:mm:ss", new Date()));
        System.out.println(DateUtil.getDate("yyyy-MM-dd HH:mm:ss", "2017-05-01 01:02:03").toString());
        System.out.println(DateUtil.getTime());
    }

    @Test
    public void ResourceTest() {
        String root = ResourceUtil.getAbsolutePath();
        System.out.println(root);

        String path = ResourceUtil.getAbsolutePath("logback.xml");
        System.out.println(path);
    }

    @Test
    public void FileTest() throws Exception {
        String xml = FileUtil.readToString(ResourceUtil.getAbsolutePath("logback.xml"));
        System.out.println(xml);
        FileUtil.saveText(ResourceUtil.getAbsolutePath() + "/empty.txt", "1\r\n2\r\n3");
        FileUtil.saveUrl(ResourceUtil.getAbsolutePath() + "/logo.png", "https://www.baidu.com/img/bd_logo1.png");
    }
}
