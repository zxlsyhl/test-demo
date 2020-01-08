package org.zxl.testdemo.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    Jedis jedis;
    String ipAddr = "localhost";
//    String ipAddr = "localhost";
    int port = 6379;
    String password = "123456";

    @Before
    public void init(){
        jedis = new Jedis(ipAddr, port);
        jedis.auth(password);
    }

    @Test
    public void test1(){
        //System.out.println(jedis.flushDB());// 清空数据
        //echo打印功能
        System.out.println(jedis.echo("namesss"));
        if(!jedis.exists("name")){
            System.out.println(jedis.set("name","zxl"));
        }
        System.out.println(jedis.get("name"));

        if(!jedis.exists("name2")){
            System.out.println(jedis.set("name2","zxl2"));
        }
        System.out.println(jedis.get("name2"));


        //随机返回key
        System.out.println("jedis.randomKey():"+jedis.randomKey());
    }

}
