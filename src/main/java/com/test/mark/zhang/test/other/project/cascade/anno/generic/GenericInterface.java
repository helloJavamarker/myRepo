package com.test.mark.zhang.test.other.project.cascade.anno.generic;

/**
 * @author by mark
 * @Classname GenericInteface
 * @Description TODO
 * @Date 2021/7/11 9:14 下午
 */
public interface GenericInterface<T> {
    /**
     * 泛型接口使用时要注意:
     *      实现类不是泛型类,接口要明确数据类型
     *      实现类也是泛型类,实现类和接口的泛型类型要一致
     */

    T getKey();
}
//不写,就是object,不能是泛型接口---要明确父类的类型
class Apple implements GenericInterface{

    //返回的是object
    @Override
    public Object getKey() {
        return null;
    }
}


class Banana implements GenericInterface<String> {

    //返回的是string
    @Override
    public String getKey() {
        return null;
    }
}

//子类可以比父类多
class Pair<T,E> implements GenericInterface<T> {

    private T key;
    private E value;
    @Override
    public T getKey() {
        return key;
    }
}