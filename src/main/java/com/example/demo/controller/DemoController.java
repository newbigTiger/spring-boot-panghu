package com.example.demo.controller;

import com.example.demo.controller.request.DemoRequest;
import com.example.demo.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 梁珑普
 * @date 2021/3/10 下午 9:55
 */
@RestController
public class DemoController {

    @Autowired
    ISysUserService iSysUserService;

    @ApiOperation("测试一下下")
    @PostMapping(value = "/queryList")
    public String getHello(@RequestBody DemoRequest demoRequest){
        try{

            return "hello";
        }catch (Exception ex){
            ex.printStackTrace();
            return "请联系胖虎";
        }
    }

    @ApiOperation("测试一下hello")
    @PostMapping("/hello")
    public String hello() {
        HashMap<String, Object> hashMap = new HashMap<>();
        Map<String, Object> associationMap = iSysUserService.queryAssociation(hashMap);
        Timestamp modifiedEnd = Timestamp.valueOf(MapUtils.getString(associationMap, "modifiedEnd"));
        Timestamp modifiedBegin = Timestamp.valueOf(MapUtils.getString(associationMap, "modifiedBegin"));
        //获取到时间戳开始和结束的区间，通过这个区间去order表中拉取数据
        Long timeStart = modifiedBegin.getTime();
        Long timeEnd = modifiedEnd.getTime();
        return "Hello Spring Boot!";
    }

    @ApiOperation("测试一下Http")
    @PostMapping(value = "/queryHttp")
    public List<String> synchroShopAssociation(){
        List<String> list = new ArrayList<>();
        list.add("1");
        List<String> integer = iSysUserService.findShopOrderAssociationNumberBySoId(list);
        return integer;
    }

    @ApiOperation("测试一下saveDemo")
    @PostMapping(value = "/saveDemo")
    public void saveDemo(@RequestBody JSONObject jsonObject){
        String keys = "orders";
    }


}
