package org.zxl.testdemo.redis;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class RedisTemplateTest2 {


    /**
     * 单节点、线程池测试
     */
    @Test
    public void test1(){
        RedisTemplate redisTemplate = new RedisTemplate();
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("localhost");
        redisStandaloneConfiguration.setDatabase(1);
        redisStandaloneConfiguration.setPassword(RedisPassword.of("123456"));
        redisStandaloneConfiguration.setPort(6379);

        //设置连接池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);//最大连接数
        jedisPoolConfig.setMaxIdle(20);//最小空闲连接数
        jedisPoolConfig.setMaxWaitMillis(10000);//当池内没有连接可用时，最大等待时间

        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcc =
                 (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        jpcc.poolConfig(jedisPoolConfig);

        JedisClientConfiguration jedisClientConfiguration = jpcc.build();

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);

        //设置序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
//        redisTemplate.setValueSerializer(new FastJsonRedisSerializer(Object.class));

        redisTemplate.afterPropertiesSet();

        System.out.println(redisTemplate.opsForValue().get("num"));
    }


    /**
     * 哨兵模式测试
     */
    @Test
    public void test2(){
        RedisTemplate redisTemplate = new RedisTemplate();
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        redisSentinelConfiguration.setMaster("master-node");
        Set<RedisNode> sentinels = new HashSet<>();
        RedisNode redisNode1 = new RedisNode("localhost", 6379);
//        RedisNode redisNode2 = new RedisNode("localhost", 6378);
        sentinels.add(redisNode1);
//        sentinels.add(redisNode2);
        redisSentinelConfiguration.setSentinels(sentinels);
        redisSentinelConfiguration.setPassword(RedisPassword.of("123456"));
        redisSentinelConfiguration.setSentinelPassword(RedisPassword.of("123456"));

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisSentinelConfiguration);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);


        //设置序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.afterPropertiesSet();

        System.out.println(redisTemplate.opsForValue().get("num"));
    }

    /**
     * 集群模式
     */
    @Test
    public void test3(){
        RedisTemplate redisTemplate = new RedisTemplate();
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();

        Set<RedisNode> redisNodes = new HashSet<>();
        RedisNode redisNode1 = new RedisNode("localhost", 6379);
//        RedisNode redisNode2 = new RedisNode("localhost", 6378);
        redisNodes.add(redisNode1);
//        sentinels.add(redisNode2);
        redisClusterConfiguration.setClusterNodes(redisNodes);
        redisClusterConfiguration.setPassword(RedisPassword.of("123456"));
        redisClusterConfiguration.setMaxRedirects(3);

        //设置连接池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);//最大连接数
        jedisPoolConfig.setMaxIdle(20);//最小空闲连接数
        jedisPoolConfig.setMaxWaitMillis(10000);//当池内没有连接可用时，最大等待时间


        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration,jedisPoolConfig);

        redisTemplate.setConnectionFactory(jedisConnectionFactory);

        //设置序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.afterPropertiesSet();

        System.out.println(redisTemplate.opsForValue().get("num"));
    }

}
