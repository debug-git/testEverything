package com.demo.classLoder;

import java.math.BigDecimal;

public class Student {
    private int age = 10;
    private String name = "";
    private BigDecimal money;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Student(int age, String name, BigDecimal money) {
        this.age = age;
        this.name = name;
        this.money = money;
    }

    static {
        System.out.println("这是学生类的静态代码块");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
