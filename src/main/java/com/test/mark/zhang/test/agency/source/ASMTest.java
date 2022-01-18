package com.test.mark.zhang.test.agency.source;

import com.test.mark.zhang.service.test.TestService;
import org.apache.ibatis.javassist.CannotCompileException;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.CtNewMethod;
import org.apache.ibatis.javassist.NotFoundException;

/**
 * @author by mark
 * @Classname ASMTest
 * @Description TODO
 * @Date 2021/12/25 4:16 下午
 */
public class ASMTest {

    public static void main(String[] args) throws Exception {
        //view --> show bytecode
        //jdk动态代理和这里实现类似,不过是自己生成的字节码,而不是借助asm生成
        TestService proxy = createProxy();
        proxy.sayHello("zhangsan");

        TestService proxy2 = createProxy2(TestService.class, "{System.out.println(\"hello:\" + $1);}");
        proxy.sayHello("zhangsan22");
    }

    /**
     * 并没有接口实现类
     *
     * 字节码作用于运行期,一般可以在运行期改变类的状态
     * @return
     * @throws NotFoundException
     * @throws CannotCompileException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static TestService createProxy() throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException {
        ClassPool classPool = new ClassPool();  // javassist --->ASM --> 编译jvm指令
        classPool.appendSystemPath();
        CtClass class1 = classPool.makeClass("TestServiceImpl");
        class1.addInterface(classPool.get(TestService.class.getName()));
        //创建一个方法
        CtMethod sayHello = CtNewMethod.make(CtClass.voidType, "sayHello", new CtClass[]{classPool.get(String.class.getName())},
                new CtClass[0], "{System.out.println(\"hello:\" + $1);}", class1);
        class1.addMethod(sayHello);
        Class aClass = classPool.toClass(class1);
        //实例化对象
        return (TestService) aClass.newInstance();
    }

    public interface TestService {
        void sayHello(String name);
    }

    public static <T> T createProxy2(Class<T> classInterface, String src) throws Exception {
        ClassPool classPool = new ClassPool();  // javassist --->ASM --> 编译jvm指令
        classPool.appendSystemPath();
        CtClass class1 = classPool.makeClass("TestServiceImpl2");//这里类名不能重复
        class1.addInterface(classPool.get(classInterface.getName()));
        //创建一个方法
        CtMethod sayHello = CtNewMethod.make(CtClass.voidType, "sayHello", new CtClass[]{classPool.get(String.class.getName())},
                new CtClass[0], src, class1);
        class1.addMethod(sayHello);
        Class aClass = classPool.toClass(class1);
        //实例化对象
        return (T) aClass.newInstance();
    }

    public static <T> T createProxy3(Class<T> classInterface, String src, String method) throws Exception {
        ClassPool classPool = new ClassPool();  // javassist --->ASM --> 编译jvm指令
        classPool.appendSystemPath();
        CtClass class1 = classPool.makeClass("TestServiceImpl2");//这里类名不能重复
        class1.addInterface(classPool.get(classInterface.getName()));
        //创建一个方法
        CtMethod sayHello = CtNewMethod.make(CtClass.voidType, method, new CtClass[]{classPool.get(String.class.getName())},
                new CtClass[0], src, class1);
        class1.addMethod(sayHello);
        Class aClass = classPool.toClass(class1);
        //实例化对象
        return (T) aClass.newInstance();
    }

    public static <T> T createProxyFinal(Class<T> classInterface, InvocationHandler handler) throws Exception {
        //创建一个类
        //创建这个接口下的所有方法实现
        //添加属性,保存handler字段
        //实例化对象


        return null;
    }

    public interface InvocationHandler {
        Object invoke(String methodName, Object args[]);
    }

    public class InvocationHandlerImpl implements InvocationHandler{

        @Override
        public Object invoke(String methodName, Object[] args) {
            System.out.println("hello");
            return null;
        }
    }

    public interface TestService2 {
        void sayHello(String name);
    }

    public interface TestService3 {
        void sayHello(String name);
        String sayHello2(String name);
        void sayHello3(String name, int age);
    }
}
