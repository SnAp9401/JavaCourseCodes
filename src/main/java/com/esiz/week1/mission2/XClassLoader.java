package com.esiz.week1.mission2;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

public class XClassLoader extends ClassLoader {
    private String path;

    public XClassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = this.decodeBytes(name);
        // 根据字节码返回这个类
        return defineClass(name, bytes, 0, bytes.length);
    }
    
    private byte[] decodeBytes(String name) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path + '/' + name + ".xlass");
        } catch (FileNotFoundException e) {
            System.out.println("Can't find " + name + ".xclass file!!!");
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int ch = 0;
        try {
            while ((ch = fis.read()) != -1)
                bos.write(255 - ch);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    public static void main(String[] args) throws Exception {
        com.esiz.week1.mission2.XClassLoader xClassLoader = new com.esiz.week1.mission2.XClassLoader(
                "D:\\demo\\src\\main\\resources\\static");
        // 反射调用
        Class<?> clazz = xClassLoader.findClass("Hello");
        Method method = clazz.getMethod("hello", null);
        Object instance = clazz.newInstance();
        method.invoke(instance, null);
    }
}
