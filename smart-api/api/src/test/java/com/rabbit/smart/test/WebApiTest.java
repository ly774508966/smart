package com.rabbit.smart.test;

import com.rabbit.smart.util.io.HttpUtil;
import org.junit.Test;

public class WebApiTest {

    @Test
    public void CookieTest() {
        //安卓全局捕获请求，setCookie存为全局变量，每次请求时携带Cookie
        HttpUtil.doGet("http://localhost:10000/account/captcha.png?i=0.246238970079516");
    }

}
