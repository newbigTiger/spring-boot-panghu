package com.example.demo.Utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RedisCatch implements ApplicationRunner {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Object> stuList = new ArrayList<>();
        Student Student = new Student();
        Student.setId(1);
        Student.setAge(18);
        Student.setDept("软件部");
        Student.setName("张三");

        Student stu1 = new Student();
        stu1.setId(2);
        stu1.setAge(18);
        stu1.setDept("软件部");
        stu1.setName("王五");

        Student stu2 = new Student();
        stu2.setId(3);
        stu2.setAge(18);
        stu2.setDept("财务部");
        stu2.setName("李四");

        stuList.add(Student);
        stuList.add(stu1);
        stuList.add(stu2);
        redisUtil.lSet("Student", stuList);
        System.out.println("=================Redis缓存加载完毕=================");
    }
}
