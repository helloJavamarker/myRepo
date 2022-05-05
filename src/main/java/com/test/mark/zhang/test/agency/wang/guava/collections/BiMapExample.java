package com.test.mark.zhang.test.agency.wang.guava.collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

/***************************************
 * @author:Alex Wang
 * @Date:2018/1/14
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class BiMapExample {
    @Test
    public void BimapTest(){
        BiMap<Integer,String> logfileMap = HashBiMap.create();
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log");
        System.out.println("logfileMap:"+logfileMap);
        BiMap<String,Integer> filelogMap = logfileMap.inverse();
        System.out.println("filelogMap:"+filelogMap);
    }

    @Test
    public void BimapTest2(){
        BiMap<Integer,String> logfileMap = HashBiMap.create();
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log");
        logfileMap.put(4,"d.log");
        logfileMap.put(5,"d.log");
        //java.lang.IllegalArgumentException: value already present: d.log
        //这里value不能重复,否则会报错
    }

    @Test
    public void BimapTest3(){
        BiMap<Integer,String> logfileMap = HashBiMap.create();
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log");

        logfileMap.put(4,"d.log");
        logfileMap.forcePut(5,"d.log");
        System.out.println("logfileMap:"+logfileMap);
        //forcePut不会报错,但相同的value的key会被覆盖  logfileMap:{1=a.log, 2=b.log, 3=c.log, 5=d.log}
    }

    @Test
    public void BimapTest4(){
        BiMap<Integer,String> logfileMap = HashBiMap.create();
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log");
        System.out.println("logfileMap:"+logfileMap);
        BiMap<String,Integer> filelogMap = logfileMap.inverse();
        System.out.println("filelogMap:"+filelogMap);

        logfileMap.put(4,"d.log");

        System.out.println("logfileMap:"+logfileMap);
        System.out.println("filelogMap:"+filelogMap);
        //filelogMap:{a.log=1, b.log=2, c.log=3, d.log=4}
        //对于反转后的map的所有操作都会影响原先的map对象
    }

}
