package com.rabbit.smart.util.io;

/**
 * Created by xuzhang on 2017/8/23.
 */
public class ResourceUtil {

    /**
     * 获取某个资源的绝对路径
     *
     * @param resouce 项目下的某个资源
     * @return 绝对路径
     */
    public static String getAbsolutePath(String resouce) {
        return ResourceUtil.class.getClassLoader().getResource(resouce).getPath();
    }

    /**
     * 获取项目的根路径
     *
     * @return 根路径
     */
    public static String getAbsolutePath() {
        return getAbsolutePath("");
    }
}
