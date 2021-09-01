package com.example.demo.Utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String,Object>redisTemplate;

    public RedisUtil(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置过期时间
     * @param key 主键
     * @param time 过期时间
     * @return 是否成功
     */
    public boolean expire(String key,long time){
        try{
            if(time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 获取这个键值对的过期时间
     * @param key 主键
     * @return 过期时间，单位秒
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 是否包含主键
     * @param key 主键
     * @return 是否存在
     */
    public Boolean hasKey(String key){
        try{
            return redisTemplate.hasKey(key);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 删除元素
     * @param keys 需要删除的元素
     */
    @SuppressWarnings("unchecked")
    public Boolean del(String ... keys){
        Boolean delete =false;
        if(keys!=null||keys.length>0) {
            if (keys.length == 1) {
                delete = redisTemplate.delete(keys[0]);
            } else {
                delete = redisTemplate.delete(String.valueOf(CollectionUtils.arrayToList(keys)));
            }
        }
        return delete;
    }
//------------------------------------String类型操作---------------------------------------------
    /**
     *
     * @param key
     * @return
     */
    public Object get(String key){
        return key == null?null:redisTemplate.opsForValue().get(key);
    }

    /**
     * 添加redis键值对
     * @param key 主键
     * @param object 对象
     * @return 添加失败or成功
     */
    public boolean set(String key,Object object){
        try{
            redisTemplate.opsForValue().set(key,object);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 设置带有过期时间的键值对
     * @param key 主键
     * @param value 内容
     * @param time 过期时间 过期时间如果是<=0那么他就是无限期
     * @return 返回是否保存成功
     */
    public boolean set(String key,Object value,long time){
        try{
            if(time>0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            }else{
                set(key,value);
            }
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 递增，适用于高并发场景下的一个订单号码的产生
     * @param key 主键
     * @param delta 递增因子
     * @return 返回递增后的值
     */
    public long incr(String key,long delta){
        if(delta<=0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }

    /**
     * 递减
     * @param key 主键
     * @param delta 递减因子
     * @return 返回递减后的值
     */
    public long decr(String key,long delta){
        if(delta<=0){
            throw new RuntimeException("delta的值必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,-delta);
    }

    //================================Map======================================

    /**
     * 获取HashGet
     * @param key 主键
     * @param item 不能为null
     * @return 返回值
     */
    public Object hget(String key,String item){
        return redisTemplate.opsForHash().get(key,item);
    }

    /**
     * 获取全部这个hash的key
     * @param key 查询主键
     * @return 全部的内容
     */
    public Map<Object,Object> hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 设置HashMap的内容
     * @param key 主键
     * @param hashMap 需要存放的值
     * @return 是否添加成功
     */
    public Boolean hmset(String key, Map<String,Object> hashMap){
        try{
            redisTemplate.opsForHash().putAll(key,hashMap);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 设置带有过期时间的Hash
     * @param key 主键
     * @param hashMap 内容
     * @param time 过期时间
     * @return 返回是否成功
     */
    public Boolean hmset(String key,Map<String,Object>hashMap,long time){
        try{
            redisTemplate.opsForHash().putAll(key,hashMap);
            if(time>0){
                expire(key,time);
            }
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 存放的Hash新增键值，如不存在则创建
     * @param key 主键
     * @param item hash主键
     * @param value 内容
     * @return 是否成功
     */
    public Boolean hset(String key,String item,Object value){
        try{
            redisTemplate.opsForHash().put(key,item,value);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 给hash表插入值，如不存在则新建
     * @param key 主键
     * @param item 次主键
     * @param value 内容
     * @param time 时间，如果已经存在了过期时间，那么这个值将会替代原过期时间
     * @return 是否成功
     */
    public Boolean hset(String key,String item,Object value,long time){
        try{
            redisTemplate.opsForHash().put(key,item,value);
            if(time>0){
                expire(key,time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除一部分的值
     * @param key 主键不能为空
     * @param item 要删除的，可以有多个
     * @return 数目
     */
    public void hdel(String key, Object ... item){
        redisTemplate.opsForHash().delete(key,item);
    }

    /**
     * hash表是否包含这个的键值
     * @param key 主键
     * @param item 项
     * @return 返回值是否成功
     */
    public Boolean hHasKey(String key,Object item){
        return redisTemplate.opsForHash().hasKey(key,item);
    }

    /**
     * 递增
     * @param key 主键
     * @param item 项
     * @param delta 递增因子
     * @return 增值后的数目
     */
    public long hincr(String key,String item,long delta){

        return redisTemplate.opsForHash().increment(key,item,delta);
    }

    /**
     * 递减
     * @param key 主键
     * @param item 项
     * @param delta 递减因子
     * @return 递减后的数目
     */
    public long hdecr(String key,String item,long delta){
        return redisTemplate.opsForHash().increment(key,item,-delta);
    }

//==============================Set===================================

    /**
     * 获取这个内部自动有序的不含重复元素的集合
     * @param key 主键
     * @return 返回值
     */
    public Set<Object> sget(String key){
        try {
            return redisTemplate.opsForSet().members(key);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value判断是否包含这个的值
     * @param key 主键
     * @param value 内容
     * @return 返回是否包含
     */
    public Boolean sHasKey(String key,Object value){
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 插入一个set集合
     * @param key 主键
     * @param value 内容，可以是多个
     * @return 插入成功的数目
     */
    public long sSet(String key,Object ... value){
        try {
            return redisTemplate.opsForSet().add(key, value);
        }catch (Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }

    /**
     * 插入值set以及设置过期时间
     * @param key 主键
     * @param time 过期时间单位是秒
     * @param value 内容
     * @return 返回插入成功的数据
     */
    public long sSetAndTime(String key,long time,Object ... value){
        try{
            Long add = redisTemplate.opsForSet().add(key, value);
            if(time>0){
                expire(key,time);
            }
            return add;
        }catch (Exception ex){
            ex.printStackTrace();
            return 0;
        }

    }

    /**
     * 返回这个key内容的长度
     * @param key 主键
     * @return 返回他的长度
     */
    public long sGetSetSize(String key){
        try{
            return redisTemplate.opsForSet().size(key);
        }catch (Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除一部分的value
     * @param key 主键
     * @param value 需要移除的内容
     * @return 一共移除了多少
     */
    public long setRemove(String key,Object ... value){
        try{
            return redisTemplate.opsForSet().remove(key,value);
        }catch (Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }

//====================================List======================================

    /**
     * 获取list缓存
     * @param key 主键
     * @param start 开始
     * @param end 结束  0 到-1表示全部
     * @return 返回list
     */
    public List<Object> lget(String key,long start,long end){
        try{
            return redisTemplate.opsForList().range(key,start,end);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 获取这个list的长度
     * @param key 主键
     * @return 一共有多少长度
     */
    public long lGetListSize(String key){
        try{
            return redisTemplate.opsForList().size(key);
        }catch (Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取下标的内容
     * @param key 主键
     * @param index 如果是负数 -1代表倒数第一个 -2表示倒数第二个 以此类推
     * @return 该下标位置的值
     */
    public Object lGetIndex(String key,long index){
        try{
            return redisTemplate.opsForList().index(key,index);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 插入单个元素
     * @param key 主键
     * @param value 插入元素
     * @return 是否插入成功
     */
    public boolean lSet(String key,Object value){
        try{
            redisTemplate.opsForList().rightPush(key,value);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 插入一个存在过期时间的元素
     * @param key 主键
     * @param value 内容
     * @param time 过期时间
     * @return 内容
     */
    public boolean lSet(String key,Object value,long time){
        try{
            redisTemplate.opsForList().rightPush(key,value);
            if(time>0){
                expire(key,time);
            }
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 插入一个完整的List元素
     * @param key 主键
     * @param objectList 插入元素集合
     * @return 返回是否成功
     */
    public boolean lSet(String key,List<Object> objectList){
        try{
            redisTemplate.opsForList().rightPushAll(key,objectList);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 插入一个完整的list同时设置一个过期时间
     * @param key 主键
     * @param objectList 插入的链表集合
     * @param time 过期时间
     * @return 是否插入成功
     */
    public boolean lSet(String key,List<Object> objectList,long time){
        try{
            redisTemplate.opsForList().rightPushAll(key,objectList);
            if(time>0){
                expire(key,time);
            }
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 修改值
     * @param key 主键
     * @param index 下标
     * @param value 修改值
     * @return 是否成功
     */
    public boolean lUpdateIndex(String key,long index,Object value){
        try{
            redisTemplate.opsForList().set(key,index,value);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 移除缓存数据
     * @param key 主键
     * @param count 移除的数量
     * @param value 移除的值
     * @return 移除了几个
     */
    public long lRemove(String key,long count,Object value){
        try{
            return redisTemplate.opsForList().remove(key,count,value);
        }catch (Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }

}