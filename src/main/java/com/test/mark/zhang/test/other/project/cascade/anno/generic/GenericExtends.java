package com.test.mark.zhang.test.other.project.cascade.anno.generic;

/**
 * @author by mark
 * @Classname GenericExtends
 * @Description 泛型继承
 * @Date 2021/7/11 8:59 下午
 */
public class GenericExtends {

    /**
     * 子类也是泛型类,子类和父类的泛型类型要一致
     *      class ChildGeneric<T> extends Generic<T>
     *          eg:public interface List<E> extends Collection<E> {
     *
     * 子类不是泛型类,父类要明确泛型的数据类型
     *      class ChildGeneric extends Generic<String>
     */
}
class Parent<E>{
    private E value;

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}

///Parent<T>中的t必须是t,要不就不写,不写父类泛型是object
//父类至少要有一个子类中的泛型,子类可以有多个泛型
class Child1<T,K,M> extends Parent<T> {
    @Override
    public T getValue() {
        return super.getValue();
    }
}

//子类如果不指定泛型类型,则父类也不能带泛型,因为此时创建子类的时候无法指定父类的类型
//子类不是泛型类的时候,必须明确父类的数据类型  要么和子类一样也不是泛型类,要么明确一个类型
//class Child2 extends Parent<T> {
class Child2 extends Parent<String> {

}
