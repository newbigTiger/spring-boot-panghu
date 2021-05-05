package com.example.demo.mothod.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 胖虎
 * @date 2021/3/20 下午 10:46
 */
public class CloanDemoTest implements Serializable{

    /**
     * 地址
     */
    private List<String> address;

    public static void main(String[] args){
        CloanDemoTest cloanDemoTest = new CloanDemoTest();
        List<String> arrayList = new ArrayList<>();
        arrayList.add("中国");
        arrayList.add("北京");
        cloanDemoTest.setAddress(arrayList);
        cloanDemoTest.deepClone();
    }
    public  CloanDemoTest deepClone(){
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream  = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (CloanDemoTest)objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }
}
