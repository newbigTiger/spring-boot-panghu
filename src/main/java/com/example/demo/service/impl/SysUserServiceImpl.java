package com.example.demo.service.impl;

import com.example.demo.entity.SysUser;
import com.example.demo.mapper.SysUserMapper;
import com.example.demo.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.MapUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author 胖虎
 * @date 2021/3/18 下午 12:16
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public List<Map<String, Object>> querySysUserList(Map<String, Object> paramMap) {
        try {
            Optional.ofNullable(paramMap).orElseThrow(Exception::new);
            return sysUserMapper.queryAny(paramMap);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("数据库异常!");
        }
    }

    @Override
    public Map<String, Object> queryAssociation(Map<String, Object> paramMap) {

        return sysUserMapper.queryAssociation(paramMap);
    }

    @Override
    public Integer insertStores(List<Map<String, Object>> paramMap) {

        return sysUserMapper.insertStores(paramMap);
    }

    @Override
    public List<String> findShopOrderAssociationNumberBySoId(List<String> soIdList) {
        return sysUserMapper.findShopOrderAssociationNumberBySoId(soIdList);
    }

    @Override
    public Integer saveShopOrderAssociation(List<Map<String, Object>> mapList) {

        return sysUserMapper.saveShopOrderAssociation(mapList);
    }
}
