package com.example.demo.service;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 胖虎
 * @date 2021/3/18 下午 12:15
 */

public interface ISysUserService {
    public List<Map<String,Object>> querySysUserList(Map<String,Object> paramMap);
    public Map<String,Object> queryAssociation(Map<String,Object> paramMap);
    Integer insertStores(List<Map<String,Object>> paramMap);
    /**
     * 根据唯一主键查询他
     * @param soIdList 主键集合
     * @return 查询到了哪些是有的，最好查不到。
     */
    List<String> findShopOrderAssociationNumberBySoId(List<String>soIdList);

    Integer saveShopOrderAssociation(List<Map<String,Object>> mapList);
}
