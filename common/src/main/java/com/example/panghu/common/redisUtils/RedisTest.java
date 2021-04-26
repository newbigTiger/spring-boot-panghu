package com.example.panghu.common.redisUtils;

import redis.clients.jedis.Jedis;

public class RedisTest {
    /**
     * 时间戳
     */
    private String ts = Long.toString(System.currentTimeMillis()/1000L);

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
        jedis.auth("panghu");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        String tockenKey = TockenUtils.tockenFactory();

        jedis.set(tockenKey,"......");

        System.out.println("tocken:生成为："+jedis.get(tockenKey)+tockenKey);
    }
}
