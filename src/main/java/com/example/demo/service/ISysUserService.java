package com.example.demo.service;


import java.util.List;
import java.util.Map;

/**
 * @author 胖虎
 * @date 2021/3/18 下午 12:15
 */

public interface ISysUserService {
    public List<Map<String,Object>> querySysUserList(Map<String,Object> paramMap);
}
