package com.example.demo.Utils.tree;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TreeRed {
    public static JSONArray listToTree(JSONArray arr, String id, String pid, String child){
        JSONArray r = new JSONArray();
        JSONObject hash = new JSONObject();
        //将数组转为Object的形式，key为数组中的id
        for(int i=0;i<arr.size();i++){
            JSONObject json = (JSONObject) arr.get(i);
            hash.put(json.getString(id), json);
        }
        //遍历结果集
        for(int j=0;j<arr.size();j++){
            //单条记录
            JSONObject aVal = (JSONObject) arr.get(j);
            //在hash中取出key为单条记录中pid的值
            JSONObject hashVP = (JSONObject) hash.get(aVal.get(pid).toString());
            //如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
            if(hashVP!=null){
                //检查是否有child属性
                if(hashVP.get(child)!=null){
                    JSONArray ch = (JSONArray) hashVP.get(child);
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }else{
                    JSONArray ch = new JSONArray();
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }
            }else{
                r.add(aVal);
            }
        }
        return r;
    }

    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add("1");
        System.out.println(a.size());
    }
}
