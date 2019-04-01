package com.demo.Stream;

import com.demo.classLoder.Student;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流的操作分为两种，分别为中间操作 和 终端操作。
 *
 * 中间操作
 * 当数据源中的数据上了流水线后，这个过程对数据进行的所有操作都称为“中间操作”。
 * 中间操作仍然会返回一个流对象，因此多个中间操作可以串连起来形成一个流水线。
 *
 * 终端操作
 * 当所有的中间操作完成后，若要将数据从流水线上拿下来，则需要执行终端操作。
 * 终端操作将返回一个执行结果，这就是你想要的数据。
 *
 * 若已执行过终端操作,则该流无法再次使用
 */
public class TestStream {
    private static List<Student> listp = new ArrayList<>();
    static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 1);
    String[] name = {"I", "am", "person"};

    public static void main(String[] args) {
        listp.add(new Student(1, "刘邦", new BigDecimal(100)));
        listp.add(new Student(1, "刘彻", new BigDecimal(200)));
        listp.add(new Student(3, "刘洵",new BigDecimal(300)));

        try(Stream<Student> stream = listp.stream();
            Stream<Integer> listI = list.stream()){
            List<Student> collect = stream.filter(e -> e.getAge()>2).collect(Collectors.toList());
            System.out.println("筛选年龄大于2岁的:"  + collect);
            long count = listp.stream().filter(e -> e.getAge() > 2).count();
            System.out.println("满足条件的学生有几人?" + count);

            //去重distinct-去掉重复的结果：
            List<Integer> collectInt = listI.distinct().collect(Collectors.toList());
            System.out.println("去重:" + collectInt);

           //截取-截取流的前N个元素：
            List<Integer> limitList = list.stream().limit(4).collect(Collectors.toList());
            System.out.println("截取前4个元素:" + limitList);

            //映射-获取list中某一个属性集合
            List<String> stuStream = listp.stream().map(Student :: getName).collect(Collectors.toList());
            System.out.println("获取list中某一个属性集合:" + stuStream);

            //归约元素求和,使用Integer.sum
            int age = list.stream().reduce(0, Integer::sum);
            System.out.println("年龄总和等于" + age);
            //计算总金额
            BigDecimal totalMoney = listp.stream().map(Student::getMoney).reduce(new BigDecimal(BigInteger.ZERO), BigDecimal::add);
            System.out.println("总金额等于" + totalMoney);

            //list转Map
            //需要注意:如果有重复的key,会报错Duplicate key
            //可以用(k1, k2)-> k1来设置,如果有重复key,则保留k1,舍弃k2
            Map<Integer, Student> stuMap = listp.stream().collect(Collectors.toMap(Student::getAge, a->a, (k1, k2)->k1));
            System.out.println(stuMap);
        }

    }
}
