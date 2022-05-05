package com.test.mark.zhang.test.agency.tao.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JedisDemo {
    Jedis jedis;

    @Before
    public void init() {
        jedis = new Jedis("doitedu01", 6379);
    }


    @Test
    public void testConn() {

        // 建立一个连接
        Jedis jedis1 = new Jedis("doitedu01", 6379);
        String resp = jedis1.ping();

        System.out.println(resp);


        // 创建连接池来连接redis
        JedisPool jedisPool = new JedisPool("doitedu01", 6379);
        Jedis jedis2 = jedisPool.getResource();
        String hallo = jedis2.ping("hallo");
        System.out.println(hallo);

        jedis1.close();
        jedis2.close();

    }


    /**
     * 将一个对象存入redis
     */
    @Test
    public void testInsertObject() throws IOException {
        Person p1 = new Person("刘旺鑫", 16, 30000.0);
        Person p2 = new Person("郭依琳", 28, 3155.5);

        // 将person序列化成一个字节数组
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream objOutput = new ObjectOutputStream(bao);

        objOutput.writeObject(p1);
        byte[] p1Bytes = bao.toByteArray();


        jedis.set("p1".getBytes(), p1Bytes);


        ByteArrayOutputStream bao2 = new ByteArrayOutputStream();
        ObjectOutputStream objOutput2 = new ObjectOutputStream(bao2);
        objOutput2.writeObject(p2);
        byte[] p2Bytes = bao2.toByteArray();
        System.out.println(p2Bytes.length);
        jedis.set("p2".getBytes(), p2Bytes);

        jedis.close();
    }


    /**
     * 将一个对象从redis中取出，并反序列化
     */
    @Test
    public void testGetObject() throws Exception {

        byte[] p2Bytes = jedis.get("p2".getBytes());
        System.out.println(p2Bytes.length);

        ByteArrayInputStream baIn = new ByteArrayInputStream(p2Bytes);
        ObjectInputStream objInput = new ObjectInputStream(baIn);
        Person o = (Person) objInput.readObject();

        System.out.println(o.getName());
        System.out.println(o.getAge());
        System.out.println(o.getSalary());

        jedis.close();
    }

    /**
     * hash结构操作
     */
    @Test
    public void testHash() {


        jedis.hset("cart:xianer", "fazhan", "5");
        jedis.hset("cart:xianer", "piaorou", "1");

        HashMap<String, String> items = new HashMap<>();
        items.put("LV bag", "10");
        items.put("xiangnaier", "100");
        items.put("cigarette", "1");

        jedis.hset("cart:xianer", items);

        // 打印购物车
        Map<String, String> cartItems = jedis.hgetAll("cart:xianer");
        System.out.println(cartItems);


        jedis.close();
    }


    @Test
    public void testPipeLine() {

        Pipeline pipelined = jedis.pipelined();

        pipelined.set("a", "1");
        pipelined.pfadd("pf1", "aaa");
        pipelined.pfadd("pf1", "bbb");
        pipelined.pfadd("pf1", "ccc");
        pipelined.pfadd("pf1", "aaa");
        pipelined.pfadd("pf1", "aaa");
        pipelined.pfadd("pf1", "bbb");


        pipelined.pfadd("pf2", "aaa");
        pipelined.pfadd("pf2", "bbb");
        pipelined.pfadd("pf2", "xxx");
        pipelined.pfadd("pf2", "uuu");
        pipelined.pfadd("pf2", "xxx");
        pipelined.pfadd("pf2", "bbb");

        pipelined.pfcount("pf1");
        pipelined.pfcount("pf2");

        pipelined.pfmerge("pf_merged", "pf1", "pf2");
        pipelined.pfcount("pf_merged");


        List<Object> results = pipelined.syncAndReturnAll();

        System.out.println(results);
        jedis.close();
    }
}
