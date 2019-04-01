package com.demo.classLoder;

import java.io.*;

/**
 * 自定义类加载器
 * 重写findClass方法
 */
public class FileSystemClassLoder extends ClassLoader {
    private String rootDir;

    public FileSystemClassLoder(String rootDir) {
        this.rootDir = rootDir;
    }

    public FileSystemClassLoder() {
    }

    @Override
    protected Class<?> findClass(String name){
        byte[] classData = this.getClassData(name);
        if (classData == null){
            throw new ClassCastException();
        }else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getClassData(String className) {
        String path  = className.replace(".", File.separator) + ".class"/*this.classNameToPath(className)*/;
        try{
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            while ((bytesNumRead = is.read(buffer)) != -1){
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String className) {
        return rootDir + File.separator + className.replace(".", File.separator) + ".class";
    }
}
