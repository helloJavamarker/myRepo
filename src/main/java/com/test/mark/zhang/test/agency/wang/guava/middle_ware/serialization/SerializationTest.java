package com.test.mark.zhang.test.agency.wang.guava.middle_ware.serialization;

import com.test.mark.zhang.entity.Person;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author by mark
 * @Classname SerializationTest
 * @Description TODO
 * @Date 2022/8/8 9:08 上午
 */
public class SerializationTest<T> {

    private T getGene(T t) {
        return t;
    }

    private void run() {
        Thread thread = new Thread();

        thread.start();
    }

    public void testSerialization() throws IOException {
        //序列化后生成指定文件路径
        File file = new File("D:" + File.separator + "person.ser");
        ObjectOutputStream oos = null;
        //装饰流（流）
        oos = new ObjectOutputStream(new FileOutputStream(file));

        //实例化类
        Person per = new Person();
        per.setName("张三");
        per.setAge(23);
        oos.writeObject(per); //把类对象序列化
        oos.close();
    }

}
