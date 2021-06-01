package com.example.demo.com.test.test.jvm.week1;

import com.example.demo.com.test.entity.Person;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 *
 * https://zhuanlan.zhihu.com/p/185612299
 *
 * 双亲委派机制时JVM类加载的默认使用的机制，其原理是:当一个类加载器收到类加载任务时，会先交给自己的父加载器去完成，
 * 因此最终加载任务都会传递到最顶层的BootstrapClassLoader，只有当父加载器无法完成加载任务时，才会尝试自己来加载
 *
 * <p>双亲委派机制有什么缺陷？ 如何打破双亲委派机制？
 * 问题1：通过双亲委派机制的原理可以得出一下结论：由于BootstrapClassloader是顶级类加载器，BootstrapClassloader无法委派AppClassLoader来加载类，
 * 也就是说BootstrapClassloader中加载的类中无法使用由AppClassLoader加载的类。可能绝大部分情况这个不算是问题，因为BootstrapClassloader加载的都是基础类，
 * 供AppClassLoader加载的类调用的类。但是万事万物都不是绝对的比如经典的JAVA SPI机制。
 *
 * <p>SPI的全名为Service Provider
 * Interface，主要是应用于厂商自定义组件或插件中。在java.util.ServiceLoader的文档里有比较详细的介绍。简单的总结下java
 * SPI机制的思想：我们系统里抽象的各个模块，往往有很多不同的实现方案，比如日志模块、xml解析模块、jdbc模块等方案。面向的对象的设计里，
 * 我们一般推荐模块之间基于接口编程，模块之间不对实现类进行硬编码。一旦代码里涉及具体的实现类，就违反了可拔插的原则，
 * 如果需要替换一种实现，就需要修改代码。为了实现在模块装配的时候能不在程序里动态指明，这就需要一种服务发现机制。
 * Java SPI就是提供这样的一个机制：为某个接口寻找服务实现的机制。有点类似IOC的思想，就是将装配的控制权移到程序之外，在模块化设计中这个机制尤其重要。
 * SPI具体约定：
 * Java SPI的具体约定为：当服务的提供者提供了服务接口的一种实现之后，在jar包的META-INF/services/目录里同时创建一个以服务接口命名的文件。
 * 该文件里就是实现该服务接口的具体实现类。而当外部程序装配这个模块的时候，就能通过该jar包META-INF/services/里的配置文件找到具体的实现类名，
 * 并装载实例化，完成模块的注入。基于这样一个约定就能很好的找到服务接口的实现类，而不需要再代码里制定。jdk提供服务实现查找的一个工具类：java.util.ServiceLoader。
 *
 */
public class MyClassLoader  extends ClassLoader {

  /**
   * 1：自己写一个类加载器
   * 2：重写loadclass方法  最重要
   * 3：重写findclass方法
   */
    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadBytes(name);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] loadBytes(String name) throws Exception {
        // 我们需要读取类的路径
        String path = name.replace('.', '/').concat(".class");
        //String path = "";
        // 去路径下查找这个类
        FileInputStream fileInputStream = new FileInputStream(classPath + "/"  + path);
        int len = fileInputStream.available();

        byte[] data = new byte[len];
        fileInputStream.read(data);
        fileInputStream.close();

        return data;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                /**
                 * 直接执行findClass()...什么意思呢? 首先会使用自定义类加载器加载类, 不在向上委托, 直接由
                 * 自己执行
                 *
                 * jvm自带的类还是需要由引导类加载器自动加载
                 */
                if (!name.startsWith("com.lxl.jvm")) {
                    c = this.getParent().loadClass(name);
                } else {
                    c = findClass(name);
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader("/Users/mark/IdeaProjects/demo/src/main/java/com/example/demo/com/test/test/jvm/week1");
        Class<?> clazz = classLoader.loadClass("com.example.demo.com.test.test.jvm.week1.Obj");
        Object obj = clazz.newInstance();
        Method sout = clazz.getDeclaredMethod("sout", null);
        //obj里面必须要有sout这个方法,不然会报错
        sout.invoke(obj, null);

        //然鹅,并没有使用自定义的加载器
        System.out.println(clazz.getClassLoader().getClass().getName());
        System.out.println(Person.class.getClassLoader().getClass().getName());
    }


}
