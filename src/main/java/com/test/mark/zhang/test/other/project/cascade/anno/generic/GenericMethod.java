package com.test.mark.zhang.test.other.project.cascade.anno.generic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author by mark
 * @Classname GenericMethod
 * @Description TODO
 * @Date 2021/7/11 9:24 下午
 */
public class GenericMethod {
    /**
     * 修饰符 <T,E> 返回值类型 方法名(形参列表) {
     *     ...
     * }
     */

    //形参必须在泛型列表里面   这里M由调用的时候确定   假如泛型方法和泛型类同名,不要认为泛型方法由泛型类决定
    //泛型方法中的泛型可以理解为局部变量的泛型
    //成员方法的泛型必须和泛型类的泛型一致,泛型方法可泛型类的泛型无关
    //泛型方法更灵活
    public <E, M> E getProduct(List<M> list) {
        return null;
    }

    public static <E,M,N> void getType(E e, M m, N n) {
    }

    //泛型可变参数,相当于数组
    public static <E> void print(E...e) {
        for (E e1 : e) {

        }
    }

    //泛型继承   注意泛型擦除
    //? extends Number       泛型上限
    //List<? extends Cat>  传递的类型上限是Cat,可以是子类  ArrayList里面的addAll方法
    //泛型下限  ? super Cat   treeSet

    public void reflect() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<GenericMethod> methodClass = GenericMethod.class;
        Constructor<GenericMethod> constructor = methodClass.getDeclaredConstructor();
        GenericMethod instance = constructor.newInstance();

        // 最上面没有加泛型,那后面通过构造获取的对象,就要进行强转
        Class clazz = GenericMethod.class;
        Constructor declaredConstructor = clazz.getDeclaredConstructor();
        Object o = declaredConstructor.newInstance();


    }
}
