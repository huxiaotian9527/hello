package com.hu.base.util.redis;

import com.alibaba.fastjson.JSON;
import com.hu.base.util.RedisUtils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisClient {
    public static void main(String[] args) {
        int size =2008;
        int x = size/1000;
        for (int i = 0; i < x; i++) {
            System.out.println(1000*i+"ff"+(1000*(i+1)-1));
        }
        System.out.println(1000*x+"ff"+(size-1));
        System.out.println(8%1000);
    }

    public static void test(){
        String key = "123456";
        RedisUtils.expire(key,60*60*24*2);
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","1");
        map.put("4","2");

        Map<String,String> map1 = new HashMap<>();
        map1.put("1","1");
        map1.put("2","2");
        map1.put("3","1");
        map1.put("4","2");
        RedisUtils.rpush(key, JSON.toJSONString(map),JSON.toJSONString(map1));
        List<String> res = RedisUtils.lrange(key,0,2);
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (String str : res) {
            Map<String,Object> map2 = JSON.parseObject(str);
            mapList.add(map2);
        }
        System.out.println(res.size());
    }
}
