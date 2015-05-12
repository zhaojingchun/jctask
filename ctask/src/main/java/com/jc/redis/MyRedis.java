package com.jc.redis;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-5-12
 * Time: 下午1:49
 * To change this template use File | Settings | File Templates.
 */
public class MyRedis {
    public static void main(String[] args){
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        //查看服务是否运行
        System.out.println("Server is running: "+jedis.ping());
        //设置 redis 字符串数据
        jedis.set("w3ckey", "Redis tutorial");
        // 获取存储的数据并输出
        System.out.println("Stored string in redis:: "+ jedis.get("w3ckey"));

        //存储数据到列表中
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("tutorial-list", 0 ,5);
        for(int i=0; i<list.size(); i++) {
            System.out.println("Stored string in redis:: "+list.get(i));
        }
        Set<String> set = jedis.keys("*");
        Iterator it =  set.iterator()  ;
        while(it.hasNext()) {
            System.out.println("List of stored keys:: "+it.next());
        }
    }

}
