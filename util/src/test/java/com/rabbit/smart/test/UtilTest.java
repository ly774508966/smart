package com.rabbit.smart.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rabbit.smart.test.dto.ResponseLink;
import com.rabbit.smart.test.dto.Role;
import com.rabbit.smart.test.dto.Sick;
import com.rabbit.smart.test.dto.User;
import com.rabbit.smart.test.service.FuKeService;
import com.rabbit.smart.test.service.GuKeService;
import com.rabbit.smart.test.service.KouQiangServcie;
import com.rabbit.smart.util.ConvertUtil;
import com.rabbit.smart.util.DateUtil;
import com.rabbit.smart.util.XMLUtil;
import com.rabbit.smart.util.event.EventBus;
import com.rabbit.smart.util.io.ExcelUtil;
import com.rabbit.smart.util.io.FileUtil;
import com.rabbit.smart.util.io.HttpUtil;
import com.rabbit.smart.util.io.ResourceUtil;
import com.rabbit.smart.util.security.AESUtil;
import com.rabbit.smart.util.security.Base64Util;
import com.rabbit.smart.util.security.MD5Util;
import com.rabbit.smart.util.security.RSAUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
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
    public void HttpTest() {
        System.out.println(HttpUtil.doGet("http://www.bootcss.com/"));
        System.out.println(HttpUtil.doGet("https://www.baidu.com/"));
    }

    @Test
    public void FileTest() throws Exception {
        String xml = FileUtil.readToString(ResourceUtil.getAbsolutePath("logback.xml"));
        System.out.println(xml);
        FileUtil.saveText(ResourceUtil.getAbsolutePath() + "/empty.txt", "1\r\n2\r\n3");
        FileUtil.saveUrl(ResourceUtil.getAbsolutePath() + "/logo.png", "https://www.baidu.com/img/bd_logo1.png");
    }

    @Test
    public void XMLTest() {
        String input = "<?xml version=\"1.0\" encoding=\"gb2312\"?>" +
                "<message System=\"ATMS\" Ver=\"1.0\">" +
                "<systemtype>LINK</systemtype>" +
                "<messagetype>LINK</messagetype>" +
                "<sourceIP>127.0.0.1</sourceIP>" +
                "<targetIP>127.0.0.2</targetIP>" +
                "<vendor></vendor>" +
                "<user>张三</user>" +
                "<password>123456</password>" +
                "</message>\n";
        ResponseLink bean = XMLUtil.toJavaBean(input, ResponseLink.class);
        System.out.println(JSONObject.toJSONString(bean));

        String xml = XMLUtil.toXml(bean);
        System.out.println(xml);
    }

    @Test
    public void JSONTest() {
        //JSONObject、JSONArray基本用法
        String amap_url = "http://restapi.amap.com/v3/direction/driving?origin=%s&destination=%s&extensions=all&output=json&key=9dc7a7674fe9033d76a99072cd74c5d5&strategy=2";
        String url = String.format(amap_url, "115.385981,33.260727", "115.385134,33.258026");
        String result = HttpUtil.doGet(url);
        System.out.println(result);
        JSONObject json = JSONObject.parseObject(result);
        JSONArray steps = json.getJSONObject("route").getJSONArray("paths").getJSONObject(0).getJSONArray("steps");
        for (Object object : steps) {
            JSONObject step = (JSONObject) object;
            System.out.println(step.toJSONString());
        }

        //序列化、反序列化
        User user = new User();
        user.setName("张三");
        user.setPassword("123456");
        List<Role> roles = new ArrayList<Role>();
        for (int i = 0; i < 2; i++) {
            roles.add(new Role(String.valueOf(i), "角色" + i));
        }
        user.setRoles(roles);
        String userJson = JSONObject.toJSONString(user);
        System.out.println(userJson);
        User userBean = JSONObject.parseObject(userJson, User.class);
        System.out.println(JSONObject.toJSONString(userBean));
    }

    @Test
    public void ExcelTest() throws IOException {
        String path = ResourceUtil.getAbsolutePath("1.xlsx");
        readExcel(path);
        path = ResourceUtil.getAbsolutePath("1.xls");
        readExcel(path);

        Workbook workbook = ExcelUtil.readExcel(path);
        Sheet sheet = workbook.getSheetAt(0);
        sheet.createRow(4);
        sheet.getRow(4).createCell(0);
        sheet.getRow(4).createCell(1);
        sheet.getRow(4).getCell(0).setCellValue("添加测试行");
        sheet.getRow(4).getCell(1).setCellValue(100);
        OutputStream os = new FileOutputStream(ResourceUtil.getAbsolutePath() + "/2.xls");
        workbook.write(os);
        os.flush();
        os.close();
    }

    private void readExcel(String path) throws IOException {
        Sheet sheet = ExcelUtil.readSheet(path, 0);
        for (int i = 0; i < 4; i++) {
            Cell cell_1 = sheet.getRow(i).getCell(0);
            Cell cell_2 = sheet.getRow(i).getCell(1);
            System.out.println(ExcelUtil.getCellValue(cell_1) + "|" + ExcelUtil.getCellValue(cell_2));
        }
    }

    @Test
    public void ImageTest() throws Exception {
        String path = ResourceUtil.getAbsolutePath("1.jpg");
        String water = ResourceUtil.getAbsolutePath("water.jpg");
        String dest = ResourceUtil.getAbsolutePath();

        //按缩放比缩小
        Thumbnails.of(path)
                .size(500, 500)
                .toFile(dest + "/2.jpg");

        //不按缩放比缩小
        Thumbnails.of(path)
                .size(500, 500)
                .keepAspectRatio(false)
                .toFile(dest + "/3.jpg");

        //按比例缩小放大
        Thumbnails.of(path)
                .scale(0.25f)
                .toFile(dest + "/4.jpg");

        Thumbnails.of(path)
                .scale(1.5f)
                .toFile(dest + "/5.jpg");

        //旋转
        Thumbnails.of(path)
                .size(1280, 1024)
                .rotate(90)
                .toFile(dest + "/6.jpg");

        Thumbnails.of(path)
                .size(1280, 1024)
                .rotate(-90)
                .toFile(dest + "/7.jpg");

        //旋转
        Thumbnails.of(path)
                .size(1280, 1024)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(water)), 0.5f)
                .outputQuality(0.8f)
                .toFile(dest + "/8.jpg");

        //剪裁
        Thumbnails.of(path)
                .sourceRegion(Positions.CENTER, 400, 400)
                .size(200, 200)
                .keepAspectRatio(false)
                .toFile(dest + "/9.jpg");

        //格式转换
        Thumbnails.of(path)
                .size(1280, 1024)
                .outputFormat("png")
                .toFile(dest + "/10.png");
    }

    @Test
    public void EventTest() throws Exception {
        EventBus.addEventListener(new FuKeService(), "看病");
        EventBus.addEventListener(new GuKeService(), "看病");
        EventBus.addEventListener(new KouQiangServcie(), "看病");
        EventBus.dispatchEvent("看病", new Sick("张三", "牙疼"));
        EventBus.dispatchEvent("看病", new Sick("李四", "骨折"));
        EventBus.dispatchEvent("看病", new Sick("小红", "产检"));
    }
}
