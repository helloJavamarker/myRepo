package com.test.mark.zhang.test.other.debug;

import com.sun.org.apache.xml.internal.security.keys.content.keyvalues.ECKeyValue;
import com.test.mark.zhang.entity.Person;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Classname DebugTest
 * @Description TODO
 * @Date 2021/6/27 8:01 下午
 * @Created by mark
 */
public class DebugTest {

    public static void main(String[] args) {
        //line();
        //detailLine();
        //method();
        //exception();
        //field();

        /////////////////////////////      多线程断点      //////////////////////////////////
        //第一个线程计算100!
        /*AddThread thread1 = new AddThread(100);

        //第二个线程计算1000000!
        AddThread thread2 = new AddThread(1000000);

        thread1.setName("t1");
        thread2.setName("t2");

        thread1.start();
        thread2.start();

        try {
            //线程join,以使主线程在"线程1"和"线程2"都返回结果之前不会进一步执行
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BigInteger result = thread1.getResult().add(thread2.getResult());
        System.out.println("两值相加:" + result);*/

        //printStackTrace();
        //evaluate();
        saveResource();
        System.out.println(UUID.randomUUID());

    }

    //行断点
    public static void line() {
        System.out.println("debug");
    }

    //详细断点(源断点)
    private static  void detailLine() {
        // Breakpoint reached at com.test.mark.zhang.test.other.debug.DebugTest.detailLine(DebugTest.java:23)
        // 显示被触发的行--方法签名,,并没有断  条件选项里面勾选suspend后才会"断",   all是所有断点都会"断",thread是当前线程会"断"
        System.out.println("按住shift");
    }

    private static void method() {
        //实际会先"断"到方法的第一行
        System.out.println("method");
        Service service = new ServiceImpl();
        //假如某个接口有很多实现类,不知道具体会调用哪个,可以在接口上打方法断点,会直接"断"到实现类的第一行
        service.service();
        //直接放行,会"断"到最后一行,可以观察变量,表达式,返回值等在这个方法内的变化
    }

    //异常断点 | 全局捕获
    private static void exception() {
        Object o = null;
        o.toString();
        System.out.println("exception");
    }

    //直接打到字段属性上 nice,监控字段
    private static void field() {
        Person person = new Person("zhang",23,"play",100L);
        //断点会先进到字段第一次变化的位置--这里是构造方法里,放开,会进入下一个属性改变的位置,这里是@Data的setAge里面==注意不是下一步
        person.setAge(100);
        //下面只是对age属性进行读取,并没有改变,所以放行后不会"断"
        System.out.println(person.getAge());
        System.out.println(person);
    }

    /////////////////////////////      多线程断点 线程之间互不干扰     //////////////////////////////////
    private static class AddThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private long num;

        public AddThread(long num) {
            this.num = num;
        }

        @Override
        public void run() {
            //suspend =  thread
            System.out.println(Thread.currentThread().getName() + " 开始计算:" + num);
            add(num);
            System.out.println(Thread.currentThread().getName() + " 计算结束");
        }

        private void add(long num) {
            BigInteger f = new BigInteger("1");
            for (int n = 2; n <= num; n++) {
                f = f.add(BigInteger.valueOf(n));
                result = f;
            }
        }

        public BigInteger getResult() {
            return result;
        }
    }

    //条件表达式

    //打印堆栈信息
    private static void printStackTrace() {
        List<String> list = new ArrayList<>();
        list.add("zhang");
        list.add("zhang2");
        list.add("zhang3");
        list.add("zhang4");
        //Breakpoint reached at com.test.mark.zhang.test.other.debug.DebugTest.printStackTrace(DebugTest.java:129)
        //Breakpoint reached
        //	at com.test.mark.zhang.test.other.debug.DebugTest.printStackTrace(DebugTest.java:129)
        //	at com.test.mark.zhang.test.other.debug.DebugTest.main(DebugTest.java:47)
        //点击断点配置,more--log--勾选breakpoint hit和stack trace
        System.out.println(list);
    }

    //计算器 实时表达器解析
    private static void evaluate() {
        System.out.println("evaluate");
        Person person = new Person("zhang",23,"play",100L);
        List<Integer> list = Stream.of(1, 2, 3, 4).map(x -> x + 2).collect(Collectors.toList());

    }

    //force return |save resource
    private static void saveResource() {
        System.out.println("shit happens");
        //点击左下角,可以选择force return    shit happens也不会执行

        System.out.println("save db");
        System.out.println("save redis");
        System.out.println("send msg to mq");

        //console旁边,分别是定位到当前断点执行位置

    }

    //stream断点

    //远程debug,需要配置idea配置参数,重启服务,配置服务器参数,

}
