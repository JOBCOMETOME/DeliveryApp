package com.Jack.test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {

    @Test
    public void  test(){
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.set("username","xiaoming");

        jedis.hset("myhash", "addr", "bj");
        String hvalue = jedis.hget("myhash", "addr");
        System.out.println(hvalue);


        jedis.close();
    }
}
