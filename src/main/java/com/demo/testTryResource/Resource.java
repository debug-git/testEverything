package com.demo.testTryResource;

/**
 *
 */
public class Resource implements AutoCloseable{

    public void sayHello() throws Exception {
        System.out.println("Hello World!");
        throw new Exception("行动方法抛出异常...");
    }

    @Override
    public void close() throws Exception {
        System.out.println(this.hashCode() + "该资源被关闭了...");
        throw new Exception("关闭方法抛出异常...");
    }
}
