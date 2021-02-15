package com.itheima.mybatis.io;

import java.io.InputStream;

/**
 * 使用类加载器读取配置文件的类
 */
public class Resources {

    /**
     * 根据传入的参数, 获取一个字节输入流, 一共分为三步.
     * @param filePath
     * @return
     */
    public static InputStream getResourceAsStream(String filePath) {

        //第一步: Resources.class拿到当前类的字节码
        //第二步: 获取这个字节码的类加载器
        //第三步: 根据类加载器读取配置文件
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
