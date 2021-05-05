package com.example.demo.mothod.property;

import java.io.*;
import java.util.List;

/**
 * @author 胖虎
 * @date 2021/3/21 下午 5:48
 */
public class PropertyDemo implements Cloneable , Serializable {
    private String name;
    private String six;
    private List<String> address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSix() {
        return six;
    }

    public void setSix(String six) {
        this.six = six;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    @Override
    public PropertyDemo clone() throws CloneNotSupportedException {

        return (PropertyDemo)super.clone();
    }

    public PropertyDemo deepClone(){
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (PropertyDemo)ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
