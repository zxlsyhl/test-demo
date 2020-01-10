package org.zxl.testdemo.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

import java.util.*;

public class RedissonTest {
    RedissonClient client;
    @Before
    public void init(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        config.useSingleServer().setDatabase(2);
        config.useSingleServer().setPassword("123456");
        config.setCodec(new StringCodec()); //设置序列化
        client = Redisson.create(config);
        Iterator<String> iterator = client.getKeys().getKeys().iterator();
        System.out.println("所有key值：");
        while (iterator.hasNext()){
            System.out.print(iterator.next()+",");
        }
        System.out.println();

    }

    @After
    public void shutdown(){
        client.shutdown();
        System.out.println("连接关闭");
    }

    @Test
    public void testString(){
        String key = "num";
        client.getBucket(key).delete();
        boolean isExists = client.getBucket(key).isExists();
        if(!isExists){
            client.getBucket(key).set("123");
        }
        System.out.println(client.getBucket(key).get());
    }

    @Test
    public void testList(){
        String key = "mylist";
        client.getList(key).delete();
        client.getList(key).add("333");
        client.getList(key).add("444");
        client.getList(key).add("555");
        RList rList = client.getList(key);
        System.out.println(rList);
        System.out.println(rList.range(0,1));
    }


    @Test
    public void testHash(){
        String key = "myHash";
        boolean isexit = client.getMap(key).isExists();
        if(isexit){
            client.getMap(key).delete();
        }
        client.getMap(key).put("a","aaaaa");
        Map<String ,String> map = new HashMap<>();
        map.put("b","bbb");
        map.put("c","ccc");
        client.getMap(key).putAll(map);

        RMap<String, String> rMap = client.getMap(key);
        Set<Map.Entry<String, String>> set = rMap.entrySet();
        System.out.println("遍历hash");
        for (Map.Entry<String, String> entry:set){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }

    @Test
    public void testSet(){
        String key = "mySet";
        if(client.getSet(key).isExists()){
            client.getSet(key).delete();
        }

        client.getSet(key).add("bbb");

        String[] strs = new String[]{"ccc","ddd","eeee"};
        client.getSet(key).addAll(Arrays.asList(strs));

        System.out.println(client.getSet(key).readAll());
    }


    @Test
    public void testSortedSet(){
        String key = "sortedSetkey";
        if(client.getSortedSet(key).isExists()){
            client.getSortedSet(key).delete();
        }

        client.getSortedSet(key).add("1");
    }

    @Test
    public void testLock(){
        String lock = "mylock";
        RLock rLock = client.getLock(lock);
        rLock.lock();
        System.out.println("do something");
        rLock.unlock();


    }

    @Test
    public void testAtomic(){
        String key = "atomicLong";
        if(client.getAtomicLong(key).isExists()){
            client.getAtomicLong(key).delete();
        }
        client.getAtomicLong(key).set(0);
        for (int i = 0, j = 10 ; i<10 && j>0;i++,j--
             ) {
            client.getAtomicLong(key).incrementAndGet();
        }

        System.out.println(client.getAtomicLong(key));
    }

}
