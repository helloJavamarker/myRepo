package com.test.mark.zhang.test.agency.shengsy.java8.supplier;

import com.test.mark.zhang.test.agency.shengsy.java8.predicate.Person;

import java.util.function.Supplier;

/**
 * @author by mark
 * @Classname SupplierTest
 * @Description TODO
 * @Date 2021/12/5 8:31 下午
 */
public class SupplierTest {

    // 常用于工厂
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "hello world";
//        Supplier<Person> supplierPerson = () -> new Person("zhangsan", 23);
//        Supplier<Person> supplierPerson2 = Person::new; //这里调用的是无参构造  而且必须有无参构造
//        System.out.println(supplier.get());
//        System.out.println(supplierPerson.get().getAge());
    }
}
