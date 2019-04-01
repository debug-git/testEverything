package com.demo.classLoder;

public class testClassLoder {

    public static void main(String[] args) {
//        Student stu1 = new Student();

        //使用自定义类加载器
        Thread.currentThread().setContextClassLoader(new FileSystemClassLoder());
        FileSystemClassLoder classLoader = (FileSystemClassLoder) Thread.currentThread().getContextClassLoader();
        try {
            try {
                Class clazz = classLoader.loadClass("com.demo.classLoder.Student");
                Student stu2 = (Student) clazz.newInstance();
                System.out.println(stu2 instanceof Student);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
