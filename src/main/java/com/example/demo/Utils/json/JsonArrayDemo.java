package com.example.demo.Utils.json;

import net.sf.json.JSONArray;

public class JsonArrayDemo {
    public static void main(String[] args) {

        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        for (int i=0;i<10;i++){
            jsonArray1.add(i+"1");
            jsonArray2.add(i+"2");
        }
        JSONArray jsonArray3 = new JSONArray();
        jsonArray3.addAll(jsonArray1);
        jsonArray3.addAll(jsonArray2);
        System.out.println(jsonArray3);
    }
}
