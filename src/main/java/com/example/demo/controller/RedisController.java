package com.example.demo.controller;

import com.example.demo.Utils.redis.RedisUtil;
import com.example.demo.Utils.redis.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Resource
    private RedisTemplate<String,Object>redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/get")
    public Object getValue(String key){
        Object value = redisTemplate.opsForValue().get(key);
        return value;
    }

    @RequestMapping(value = "/set")
    public boolean setValue(String key,Object obj){
        return redisUtil.set(key,obj);
    }
    /**
     * 添加List对象数据到redis中
     * @return
     */
    @RequestMapping(value = "/setList")
    public boolean setList(String key){
        if(!redisUtil.hasKey(key)) {
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
            return redisUtil.lSet(key, stuList);
        }else{
            return true;
        }
    }

    @RequestMapping("/getList")
    public List<Object> getList(String key){

        return redisUtil.lget(key,0,-1);
    }

    @RequestMapping(value="/removeList")
    public boolean removeList(String key){

        return redisUtil.del(key);
    }

}
