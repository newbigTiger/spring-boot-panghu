package com.example.demo.controller;

import com.example.demo.service.ISysUserService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/queryList",method = RequestMethod.GET)
    public String getHello(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,@RequestBody Map<String,Object> paramMap){
        try{
            System.out.println(httpServletRequest.getHeaderNames());
            System.out.println(httpServletRequest.getHeader("token"));
            List<Map<String, Object>> mapList = iSysUserService.querySysUserList(paramMap);
            System.out.println(mapList);
            paramMap.put(null,"");
            paramMap.put(null,"");
            return "hello";
        }catch (Exception ex){
            ex.printStackTrace();
            return "请联系胖虎";
        }
    }
    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
    }

    @RequestMapping(value = "/queryHttp",method = RequestMethod.GET)
    public Map<String, Object> synchroShopAssociation(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                                      @RequestBody Map<String,Object> paramMap) throws Exception {
        Boolean checkToken = checkToken(httpServletRequest, httpServletResponse);
        System.out.println(checkToken);
        System.out.println(httpServletRequest.getHeaderNames());
        System.out.println(paramMap);
        return paramMap;
    }

    public static Boolean checkToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        // 获取我们请求头中的token验证字符
        String headerToken = httpServletRequest.getHeader("token");
        if (StringUtils.isBlank(headerToken)) {
            // 如果token不存在的话,返回错误信息。
            resp(httpServletResponse,"请检查参数配置");
            return true;
        }
        return false;
    }
    /**
     * 返回错误消息
     * @param httpServletResponse servlet返回
     * @param resultCode 错误枚举类
     * @throws Exception 错误
     */
    private static void resp(HttpServletResponse httpServletResponse, String resultCode) throws Exception{
        httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(resultCode);
    }
}
