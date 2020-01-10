package org.zxl.testdemo.sequence;

import org.junit.Before;
import redis.clients.jedis.Jedis;

/**
 * Redis实现了一个原子操作INCR和INCRBY实现递增的操作。当使用数据库性能不够时，可以采用Redis来代替，同时使用Redis集群来提高吞吐量。可以初始化每台Redis的初始值为1,2,3,4,5，然后步长为5。各个Redis生成的ID为：
 *
 * A：1，6，11，16，21
 * B：2，7，12，17，22
 * C：3，8，13，18，23
 * D：4，9，14，19，24
 * E：5，10，15，20，25
 * 优点
 *
 * 不依赖于数据库，灵活方便，且性能优于数据库。
 * 数字ID天然排序，对分页或者需要排序的结果很有帮助。
 * 缺点：
 *
 * 如果系统中没有Redis，还需要引入新的组件，增加系统复杂度。
 * 需要编码和配置的工作量比较大。这个都不是最大的问题。
 *
 */
public class RedisUtil {



    public static void main(String[] args){
        Jedis jedis;
        String ipAddr = "localhost";
        //    String ipAddr = "localhost";
        int port = 6379;
        String password = "123456";
        jedis = new Jedis(ipAddr, port);
        jedis.auth(password);
        System.out.println(jedis.incr("sequest"));
        System.out.println(jedis.incr("sequest"));
        System.out.println(jedis.incrBy("sequest", 2));
    }

}
