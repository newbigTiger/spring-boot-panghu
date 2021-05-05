package com.example.demo.Utils.streamDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 胖虎
 * @date 2021/4/19 下午 3:07
 */
public class QueryDemo {
    public static void main(String[] args) {
        QueryDemo queryDemo = new QueryDemo();
        queryDemo.groupByTest();
    }
    /**
     * ForEach遍历
     */
    public void forEachTest(){
        List<User> userList = UserService.getUserList();
        userList.forEach(System.out::println);
        //等同于
//        userList.forEach(item->{
//            System.out.println(item);
//        });
    }

    /**
     *使用filter进行筛选
     */
    public void filterList(){
        List<User> userList = UserService.getUserList();
        userList = userList.
                stream().
                filter(user -> user.getDepartment().equals("研发部")).
                collect(Collectors.toList());
        userList.forEach(System.out::println);
    }

    /**
     *获取筛选的对象
     * 需要注意一点findAny()返回的参数不能确定，对同一个列表多次调用可能返回不同的值
     * 如果是串行情况，且业务较少的情况下追求性能可以使用findAny()。
     * 否则使用findFirst
     */
    public void findAny(){
        List<User> userList = UserService.getUserList();
        //parallelStream能生成并行流
        User user1 = userList.parallelStream().filter(user -> user.getAge() == 20).findAny().orElse(null);
        User user2 = userList.parallelStream().filter(user -> user.getAge() == 20).findFirst().orElse(null);
        System.out.println(user1);
        System.out.println(user2);
    }

    /**
     * map将某一列独立出来形成新元素
     */
    public void mapTest(){
        List<User> userList = UserService.getUserList();
        //获取用户的名称列表
        List<String> stringList = userList.stream().map(User::getName).collect(Collectors.toList());
        stringList.forEach(System.out::println);
    }

    public void flatMapTest(){
        ArrayList<String> objects = new ArrayList<>();
        objects.add("北京；上海；广州");
        objects.add("山西；陕西；河北");
        objects.add("河南；湖南；东北");
        List<String> stringList = objects.stream()
                .map(item -> item.split("；"))
                .flatMap(Arrays::stream).collect(Collectors.toList());
        //遍历
        stringList.forEach(System.out::println);
    }

    /**
     *  去重复
     */
    public void distinctTest(){
        List<User> userList = UserService.getUserList();
        List<String> collect = userList.stream().map(user -> user.getDepartment()).distinct().collect(Collectors.toList());
        collect.forEach(System.out::println);

    }
    public void groupByTest(){
        //获取用户列表
        List<User> userList = UserService.getUserList();

        //根据部门和性别对用户列表进行分组
        Map<String, Map<String,List<User>>> userMap = userList.stream()
                .collect(Collectors.groupingBy(User::getDepartment,Collectors.groupingBy(User::getSex)));

        //遍历分组后的结果
        userMap.forEach((key1, map) -> {
            System.out.println(key1 + "：");
            map.forEach((key2, user) ->
            {
                System.out.println(key2 + "：");
                user.forEach(System.out::println);
            });
            System.out.println("--------------------------------------------------------------------------");
        });
    }
}
