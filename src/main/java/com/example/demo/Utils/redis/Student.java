package com.example.demo.Utils.redis;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    //id
    private Integer id;
    //姓名
    private String name;
    //年龄
    private Integer age;
    //部门
    private String dept;
}
