package com.Archerwen.task01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@SpringBootApplication
public class Task01Application extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        SpringApplication.run(task01application.class, args);
//        new Task01Application().findClass("Hello").newInstance();
        // 相关参数
        Class<?> clazz = new Task01Application().loadClass("Hello");
        // 创建对象
        Object instance = clazz.getDeclaredConstructor().newInstance();
        // 调用实例方法
        Method method = clazz.getMethod("hello");
        method.invoke(instance);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] FileBase64 = getFile();
            return defineClass(name, FileBase64, 0, FileBase64.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private static byte[] getFile() throws IOException {
        File path = new File("E:\\Code\\Hello.xlass");
        FileInputStream inputStream = new FileInputStream(path);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ~bytes[i];
        }
        return bytes;
    }


}
