package math;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * @author 胖虎
 * @date 2021/4/12 下午 6:24
 */
public class Demox {
    public static void main(String[] args) {
        String a = "[123, 456,'dfsfsd']";
        Map<String,Object>map = new HashMap<>();
        map.put("so_ids",JSONArray.fromObject(a));
        System.out.println(JSONObject.fromObject(map));
    }
}
