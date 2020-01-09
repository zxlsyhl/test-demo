package org.zxl.testdemo.redis;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class RedisTemplateTest {

    RedisTemplate redisTemplate;
    ValueOperations valueOps;
    ListOperations listOperations;
    HashOperations hashOperations;
    SetOperations setOperations;
    ZSetOperations zSetOperations;

    @Before
    public void init(){
        redisTemplate = new RedisTemplate();
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("localhost");
        redisStandaloneConfiguration.setDatabase(1);
        redisStandaloneConfiguration.setPassword(RedisPassword.of("123456"));
        redisStandaloneConfiguration.setPort(6379);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);


        //设置序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
//        redisTemplate.setValueSerializer(new FastJsonRedisSerializer(Object.class));


        redisTemplate.afterPropertiesSet();


        valueOps = redisTemplate.opsForValue();
        listOperations = redisTemplate.opsForList();
        hashOperations = redisTemplate.opsForHash();
        setOperations = redisTemplate.opsForSet();
        zSetOperations = redisTemplate.opsForZSet();
    }

    @Test
    public void test1(){
        redisTemplate.opsForValue().set("num","123");
        System.out.println(redisTemplate.opsForValue().get("num"));

    }

    /**
     * 操作string
     */
    @Test
    public void stringTest() throws InterruptedException {
        System.out.println("set 和 get 用法");
        valueOps.set("num","123");
        System.out.println(valueOps.get("num"));

        System.out.println("有效期测试");
        valueOps.set("num","123",2, TimeUnit.SECONDS);
        Thread.sleep(3000);
        System.out.println("验证过期失效："+valueOps.get("num"));

        System.out.println("设置偏移量");
        valueOps.set("key","hello world");
        valueOps.set("key","redis", 6);
        System.out.println(valueOps.get("key"));

        System.out.println("getAndSet用法");
        System.out.println(valueOps.getAndSet("key","hello lilei"));
        System.out.println(valueOps.get("key"));

        //append用法
        System.out.println("append用法");
        valueOps.append("test", "hello");
        System.out.println(valueOps.get("test"));
        valueOps.append("test", "world");
        System.out.println(valueOps.get("test"));

        System.out.println("获取value值长度");
        System.out.println(valueOps.size("key"));

    }


    /**
     * list操作
     * @throws InterruptedException
     */
    @Test
    public void listTest() throws InterruptedException {
        String key = "list";
        System.out.println("长度测试，当key对应类型不是list时报错，当key不存在时返回0");
        System.out.println(listOperations.size(key));

        System.out.println("左边插入");
        listOperations.leftPush(key, "java");
        listOperations.leftPush(key, "python");
        listOperations.leftPush(key, "C++");
        System.out.println(listOperations.range(key,0,-1));

        System.out.println("插入数组到列表中");
        String[] strs = new String[]{"1","2","3"};
        listOperations.leftPushAll(key,strs);
        System.out.println(listOperations.range(key,0,-1));


        System.out.println("插入list到列表中");
        List<String> strList = new ArrayList<>();
        strList.add("a");
        strList.add("b");
        strList.add("c");
        listOperations.leftPushAll(key,strList);
        System.out.println(listOperations.range(key,0,-1));



        System.out.println("从右侧插入");
        listOperations.rightPush(key,"right val1");

        System.out.println("指定index位置插入");
        listOperations.set(key,0,"first value");
        System.out.println(listOperations.range(key,0,-1));

        System.out.println("从列表中删除等于值得元素，count控制，顺序和个数，0删除所有，大于0从头开始，小于0从尾开始");
        listOperations.remove(key,1,"a");
        System.out.println(listOperations.range(key,0,-1));

        System.out.println("获取指定下标的值");
        System.out.println(listOperations.index(key,2));

        System.out.println("弹出测试，弹出之后该值在列表中不复存在");
        listOperations.leftPop(key);
        System.out.println(listOperations.range(key,0,-1));
        //移除集合中左边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
        listOperations.leftPop(key, 1,TimeUnit.SECONDS);
        System.out.println(listOperations.range(key,0,-1));
        Thread.sleep(2000);
        System.out.println(listOperations.range(key,0,-1));

    }

    /**
     * 操作hash
     */
    @Test
    public void hashTest(){
        String key = "hashkey";
        System.out.println("put 测试");
        hashOperations.put(key,"a", "aaaa");
        hashOperations.put(key,"b", "aaaa");
        hashOperations.put(key,"b", "bbbb");
        System.out.println("get 测试");
        System.out.println(hashOperations.get(key,"a"));

        System.out.println("delete 测试");
        System.out.println(hashOperations.delete(key,"a"));
        System.out.println("获取hash信息，返回map对象");
        System.out.println(hashOperations.entries(key));

        System.out.println("判断hashkey是否存在");
        System.out.println(hashOperations.hasKey(key,"b"));
        System.out.println(hashOperations.hasKey(key,"c"));

        System.out.println("获取key对应的散列表的key");
        System.out.println(hashOperations.keys(key));

        System.out.println("获取key对应的散列表的大小个数");
        System.out.println(hashOperations.size(key));

        //将java中map对象放入hash中
        System.out.println("将java中map对象放入hash中");
        Map<String,String> map = new HashMap<>();
        map.put("1","111");
        map.put("2","22");
        map.put("3","33");
        hashOperations.putAll(key,map);
        System.out.println(hashOperations.entries(key));


        System.out.println("获取hash中所有的值");
        System.out.println(hashOperations.values(key));

        System.out.println("使用cursor迭代");
        Cursor<Map.Entry<Object, Object>> cursor = hashOperations.scan(key, ScanOptions.NONE);
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
        }




    }


    /**
     * 操作set
     */
    @Test
    public void setTest(){
        String key = "setkey",key2 = "key2";
        System.out.println("add 操作");
        setOperations.add(key,"123");
        setOperations.add(key,"456");
        setOperations.add(key,"789");
        System.out.println(setOperations.members(key));

        String[] strs = new String[]{"str1","str2"};
        setOperations.add(key,strs);
        System.out.println(setOperations.members(key));

        System.out.println("移除集合中一个或多个成员");
        setOperations.remove(key, "123");
        System.out.println(setOperations.members(key));
        setOperations.remove(key,strs);
        System.out.println(setOperations.members(key));

        System.out.println("移除并返回集合中的一个随机元素");
        System.out.println(setOperations.pop(key));
        System.out.println(setOperations.members(key));

        System.out.println("将member元素从source集合移动到target集合");
        setOperations.move(key,"123",key2);
        System.out.println(setOperations.members(key));
        System.out.println(setOperations.members(key2));

        System.out.println("集合的长度");
        System.out.println(setOperations.size(key));

        System.out.println("cursor遍历");
        Cursor<Object> cursor = setOperations.scan(key,ScanOptions.NONE);
        while (cursor.hasNext()){
            System.out.println(cursor.next());
        }


    }


    /**
     * 有序集合操作
     */
    @Test
    public void sortedSetTest(){
        String key = "zsortSetkey";
        System.out.println("添加测试");
        zSetOperations.add(key, "123", 3.0);
        zSetOperations.add(key, "234", 1.0);
        zSetOperations.add(key, "345", 2.0);
        System.out.println(zSetOperations.range(key,0,-1));

        System.out.println("新增有序集合");
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-5",9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-6",9.9);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        System.out.println(zSetOperations.add("zset1",tuples));
        System.out.println(zSetOperations.range("zset1",0,-1));

        System.out.println("从集合中移除一个或多个元素");
        System.out.println(zSetOperations.remove(key,"123"));
        System.out.println(zSetOperations.range("zset1",0,-1));

        System.out.println("返回有序集合中指定成员的排名");
        System.out.println(zSetOperations.rank(key, "345"));

        System.out.println("通过分数返回有序集合指定区间内的成员个数");
        System.out.println(zSetOperations.rangeByScore(key,0,5));
        System.out.println(zSetOperations.count(key,0,5));


        System.out.println("返回成员个数");
        System.out.println(zSetOperations.size(key));

        System.out.println("获取指定成员的score值");
        System.out.println(zSetOperations.score(key,"345"));

        System.out.println("删除指定索引位置的成员");
        System.out.println(zSetOperations.removeRange(key,0,1));
        System.out.println(zSetOperations.range("zset1",0,-1));

        System.out.println("删除指定分数值范围的成员");
        System.out.println(zSetOperations.removeRangeByScore(key,5,10));
        System.out.println(zSetOperations.range("zset1",0,-1));


        System.out.println("cursor遍历");
        Cursor<ZSetOperations.TypedTuple<Object>> cursor = zSetOperations.scan(key,ScanOptions.NONE);
        while (cursor.hasNext()){
            ZSetOperations.TypedTuple<Object> item = cursor.next();
            System.out.println(item.getValue()+":"+item.getScore());
        }

    }






}
