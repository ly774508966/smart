package com.rabbit.smart.util.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuzhang on 2017/8/23.
 */
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    //region 文件读取
    /**
     * 读取文件
     *
     * @param path 路径
     * @return 文件文本
     */
    public static String readToString(String path) throws Exception {
        RandomAccessFile file = new RandomAccessFile(path, "r");
        byte[] bytes = new byte[(int) file.length()];
        file.read(bytes);
        file.close();
        return new String(bytes, "utf-8");

    }

    /**
     * 读取文件
     *
     * @param path 路径
     * @return 文件流
     */
    public static InputStream readToStream(String path) {
        try {
            File file = new File(path);
            if (file.isFile() && file.exists()) {
                InputStream in = new FileInputStream(file);
                return in;
            }
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 获取某个路径下的所有文件
     *
     * @param path 路径，支持文件和文件夹
     * @return 文件列表
     */
    public static List<File> getFiles(File path) {
        List<File> files = new ArrayList<File>();
        if (path.isDirectory()) {
            File[] inputSubFiles = path.listFiles();
            for (File file : inputSubFiles) {
                List<File> listFiles = getFiles(file);
                for (File listFile : listFiles) {
                    files.add(listFile);
                }
            }
        } else {
            files.add(path);
        }
        return files;
    }
    //endregion

    //region 文件保存

    /**
     * 文件保存
     *
     * @param path 路径
     * @param text 文本
     * @throws IOException 异常
     */
    public static void saveText(String path, String text) throws IOException {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        //写文件
        OutputStream out = new FileOutputStream(file.getPath());
        byte[] bytes = text.getBytes();
        out.write(bytes, 0, bytes.length);
        out.close();
    }

    /**
     * 文件保存
     *
     * @param path 路径
     * @param in   流数据
     * @throws IOException 异常
     */
    public static void saveText(String path, InputStream in) throws IOException {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        //写文件
        OutputStream out = new FileOutputStream(file.getPath());

        byte[] bytes = new byte[1024];
        int num;
        while ((num = in.read(bytes)) != -1) {
            out.write(bytes, 0, num);
        }
        in.close();
        out.close();
    }


    /**
     * 文件保存
     *
     * @param path 路径
     * @param url  请求路径
     * @throws IOException 异常
     */
    public static void saveUrl(String path, String url) throws IOException {
        InputStream stream = HttpUtil.doGetStream(url);
        saveText(path, stream);
    }
    //endregion


}
