package com.example.demo.mothod.property;

/**
 * @author 胖虎
 * @date 2021/3/21 下午 5:56
 */
public class PropertyDemoTest {

    public static void main(String[] args) {
        PropertyDemo propertyDemo = new PropertyDemo();
        PropertyDemo deepClone = propertyDemo.deepClone();
        try {
            PropertyDemo clone = propertyDemo.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
