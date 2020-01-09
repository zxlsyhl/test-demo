package org.zxl.testdemo.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        jedis.expire("name2",10);
    }

    @Test
    public void test2() throws UnsupportedEncodingException {
        System.out.println("获取key值类型"+jedis.type("name"));

        //导出key的值
        byte[] namebyte = jedis.dump("name");
        System.out.println("jedis.dump:"+new String(namebyte));

        if(!jedis.exists("name2")){
            jedis.set("name2","zqh");
        }
        jedis.renamenx("name2", "name2_new");
        System.out.println(jedis.get("name2_new"));

        Set<String> set = jedis.keys("name*");
        System.out.println(set);
    }

    /**
     * 对字符串String的相关操作
     */
    @Test
    public void testString(){
        jedis.set("hello", "hello");
        System.out.println(jedis.get("hello"));
        //使用append向字符串后面添加
        jedis.append("hello", "world");
        System.out.println(jedis.get("hello"));
        //使用set覆盖
        jedis.set("hello","hi");
        System.out.println(jedis.get("hello"));

        //设置过期时间
        jedis.setex("hello2",2,"world2");
        System.out.println(jedis.get("hello2"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println(jedis.get("hello2"));

        //一次添加多个key-value对
        jedis.mset("a","1","b","2");
        //获取a和b的value
        List<String> values = jedis.mget("a","b");
        System.out.println(values);

        //批量删除
        jedis.del("a","b");
        System.out.println(jedis.get("a"));
        System.out.println(jedis.get("b"));

    }

    /**
     * 对链表Lists的操作
     */
    @Test
    public void testList(){
        String key = "mylist";
        jedis.del(key);

        //队列添加元素
        jedis.rpush(key,"aaaa");
        jedis.rpush(key,"bbbb");
        jedis.rpush(key,"cccc");
        jedis.rpush(key,"dddd");
        jedis.rpush(key,"dddd");
        //队列的长度
        System.out.println("lenth:" + jedis.llen(key));

        //打印队列，从索引0开始，到倒数第一个（全部元素）
        System.out.println("all elements: "+ jedis.lrange(key, 0 , -1));

        //索引为1的元素
        System.out.println("index of 1: "+jedis.lindex(key, 1));

        //设置队列里面一个元素的值，当index超出范围是会返回一个error
        jedis.lset(key, 1, "aa22");
        System.out.println("index of 1:"+ jedis.lindex(key,1));

        //从队列的右边入队一个元素
        jedis.rpush(key, "-2", "-1"); //先-2，后-1入队列
        System.out.println("all emements: "+ jedis.lrange(key, 0 ,-1));

        //从队列的左边入队一个或多个元素
        jedis.lpush(key, "second element", "first element");
        System.out.println("all emements: "+ jedis.lrange(key, 0 ,-1));

        //从队列的左边出队一个元素
        System.out.println(jedis.lpop(key));
        //从队列的右边出队一个元素
        System.out.println(jedis.rpop(key));
        System.out.println("all emements: "+ jedis.lrange(key, 0 ,-1));


        // count > 0: 从头往尾移除值为 value 的元素，count为移除的个数。
        // count < 0: 从尾往头移除值为 value 的元素，count为移除的个数。
        // count = 0: 移除所有值为 value 的元素。
        jedis.lrem(key,1,"cccc");
        System.out.println("all emements: "+ jedis.lrange(key, 0 ,-1));

        System.out.println(jedis.lrange(key,0,2));
        System.out.println("all emements: "+ jedis.lrange(key, 0 ,-1));

        //删除区间以外的元素
        System.out.println(jedis.ltrim(key, 0 ,2));
        System.out.println("all emements: "+ jedis.lrange(key, 0 ,-1));

    }


    //对集合Sets的操作
    @Test
    public void testSets(){
        String key1 = "myset";
        String key2 = "myset2";
        jedis.del(key1,key2);

        //集合添加元素
        jedis.sadd(key1,"aaa","bbb","ccc");
        jedis.sadd(key2, "bbb","ccc","ddd");

        //获取集合里面的元素数量
        System.out.println("jedis.scard(key1):"+jedis.scard(key1));
        System.out.println("jedis.scard(key2):"+jedis.scard(key2));

        //获取两个集合的交集，并存储在一个关键的结果集
        Set<String> setStr = jedis.sinter(key1,key2);
        System.out.println("setStr:"+setStr);
        //获取两个集合的交集，并存储在一个关键的结果集
        jedis.sinterstore("destination",key1,key2);
        System.out.println(jedis.smembers("destination"));

        //获得两个集合的并集
        Set<String> setStr2 = jedis.sunion(key1,key2);
        System.out.println("setStr2:"+setStr2);
        //获得两个集合的并集，并存储在一个关键的结果集
        jedis.sunionstore("destination",key1,key2);
        System.out.println(jedis.smembers("destination"));

        //key1集合中，key2集合中没有的元素，并存储在一个关键的结果集
        jedis.sdiffstore("destination",key1,key2);
        System.out.println(jedis.smembers("destination"));

        //确定某个元素是一个集合的成员
        System.out.println(jedis.sismember(key1,"aaa"));

        //从key集合里面随机获取一个元素
        System.out.println(jedis.srandmember(key1));

        //aaa从key1移动到key2集合
        jedis.smove(key1,key2,"aaa");
        System.out.println(jedis.smembers(key1));
        System.out.println(jedis.smembers(key2));

        //删除并获取一个集合里面的元素
        System.out.println(jedis.spop(key1));

        //从集合里面删除一个或多个元素
        jedis.srem(key2, "ccc","ddd");
        System.out.println(jedis.smembers(key2));


    }

    /**
     * 对有序集合sortedSets的操作
     */
    @Test
    public void sortedSetsTest(){
        String key = "mysortset";
        jedis.del(key);

        Map<String, Double> scoreMembers = new HashMap<>();
        scoreMembers.put("aaa",1001.0);
        scoreMembers.put("bbb",1002.0);
        scoreMembers.put("ccc",1003.0);

        //添加数据
        jedis.zadd(key, 1004.0, "ddd");
        jedis.zadd(key, scoreMembers);

        //获取一个排序的集合中成员数量
        System.out.println(jedis.zcard(key));

        //返回成员在指定范围内的有序集合，以0表示有序集合的第一个成员，以1表示有序集合的第二个成员
        Set<String> coll = jedis.zrange(key, 0 ,-1);
        System.out.println(coll);

        //返回成员在指定范围内的逆序集合
        coll = jedis.zrevrange(key, 0, -1);
        System.out.println(coll);

        //元素下标
        System.out.println(jedis.zscore(key, "bbb"));

        //删除元素
        System.out.println(jedis.zrem(key, "aaa"));
        System.out.println(jedis.zrange(key, 0, -1));

        //给定值范围内的成员数
        System.out.println(jedis.zcount(key,1002.0,1003.0));


    }

    /**
     * 对哈希Hashs的操作
     */
    @Test
    public void hashTest(){
        String key = "myhash";
        jedis.del(key);

        Map<String, String> hash = new HashMap<>();
        hash.put("aaa","11");
        hash.put("bbb","22");
        hash.put("ccc","33");

        //添加数据
        jedis.hmset(key, hash);
        jedis.hset(key, "ddd", "44");

        //获取hash的所有元素key值
        System.out.println(jedis.hvals(key));

        //获取hash中所有的key对应的value值
        System.out.println(jedis.hvals(key));

        //获取hash里所有的元素数量
        System.out.println(jedis.hlen(key));


        //获取hash中的全部的域和值，以Map泛型的形式返回
        Map<String, String> elements = jedis.hgetAll(key);
        System.out.println(elements);

        //判断给定key值是否存在于哈希中
        System.out.println(jedis.hexists(key, "bbb"));

        //获取hash里面指定字段对应的值
        System.out.println(jedis.hmget(key,"aaa","bbb"));

        //获取指定的值
        System.out.println(jedis.hget(key,"aaa"));

        //删除指定的值
        System.out.println(jedis.hdel(key, "aaa"));
        System.out.println(jedis.hgetAll(key));

        //为key中的域field的值加上增量increment
        System.out.println(jedis.hincrBy(key, "bbb", 100));
        System.out.println(jedis.hgetAll(key));

    }

    /**
     * 操作事务：redis事务就是一次性、顺序性、排他性的执行一个队列中的一系列命令。
     * 一：发生语法错误时，所以命令都不会被执行。发生运行错误，正确的命令会被执行
     * 语法错误：语法错误指命令错误或者命令不存在。
     * 运行错误：指在命令执行时出现的错误，比如使用散列类型的命令操作集合类型的key。
     * 二：原子性（不支持）、隔离性（不支持）、一致性（不支持）、持久性（支持）
     * 三：watch，监视key值，乐观所实现。
     */
    @Test
    public void transactionTest(){
        Transaction t = jedis.multi();
        t.set("hello", "world");
        Response<String> response = t.get("hello");

        t.zadd("foo", 1, "barowitch");
        t.zadd("foo", 0, "barokey");
        t.zadd("foo", 0, "barorow");

        Response<Set<String>> sosse = t.zrange("foo", 0 , -1);
        //返回全部相应并以有序集合的方法返回
        System.out.println(response);
        System.out.println(sosse);
        t.exec(); //事务执行，不能缺少

        String foolbar = response.get(); //可以从响应中获取数据

        int soseSize = sosse.get().size();//get()会立即调用set方法
        System.out.println(foolbar);
        System.out.println(soseSize);

    }

    /**
     * 操作管道
     */
    @Test
    public void transactionPipelineTest(){
        Pipeline p = jedis.pipelined(); //开一个管道

        p.set("fool", "bar");
        p.zadd("foo", 1, "boooos");
        p.zadd("foo", 0, "boooed");
        p.zadd("foo", 0, "booobb");

        Response<String> pipeString = p.get("fool");
        Response<Set<String>> sose = p.zrange("foo", 0 ,-1);
        System.out.println(pipeString);
        System.out.println(sose);

        p.sync();//提交

        System.out.println("=================");
        System.out.println(p.get("fool"));
        System.out.println(p.zrange("foo",0,-1));

        int soseSize = sose.get().size();
        Set<String> setBack = sose.get();
        System.out.println(soseSize);
        System.out.println(setBack);

    }

}
