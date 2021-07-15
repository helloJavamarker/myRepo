package com.test.mark.zhang.test.other.project.cascade.anno.generic;

import lombok.Data;

/**
 * @author by mark
 * @Classname GenericClass
 * @Description TODO
 * @Date 2021/7/11 8:34 下午
 *
 * 视频笔记: https://blog.csdn.net/Beyondczn/article/details/107093693
 */
public class GenericClass<T> {
    //这里T相当于形参,创建对象的时候再指定类型

    /**
     * 泛型类:
     *      定义语法:
     *      class 类名称<泛型标识,泛型标识,...> {
     *          private 泛型标识 变量名;
     *          ...
     *      }
     *  常用泛型标识: T,E,K,V   通常k,v成对出现
     */

    private T key;

    public GenericClass() {

    }


    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public GenericClass(T key) {
        this.key = key;
    }

    // 泛型类使用

    public void test() {
        GenericClass<String> genericTest = new GenericClass<>();
        GenericClass<Integer> intGeneric = new GenericClass<>();
        GenericClass<Void> voidGeneric = new GenericClass<>();
        //voidGeneric.setKey("ll"); 编译不通过
    }

}
