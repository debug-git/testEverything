package com.demo.testTryResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TryResource {
    public final static Logger LOGGER  = LoggerFactory.getLogger(TryResource.class);

    /**
     *  实现了AutoCloseable 接口的资源类才可以使用try-with-resource语句
     * @param args
     */
    public static void main(String[] args) {
        try (Resource rs = new Resource(); Resource rs2 = new Resource()){
            rs.sayHello();
            rs2.sayHello();
        } catch (Exception e) {
            LOGGER.error("报错了", e);
        }
    }
}
