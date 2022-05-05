package com.test.mark.zhang.test.agency.wang.guava.collections;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/***************************************
 * @author:Alex Wang
 * @Date:2018/1/14
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class MultimapsExample {
    public static void main(String[] args) {
        Multimap<String, String> myMultimap = ArrayListMultimap.create();

        // Adding some key/value
        myMultimap.put("Fruits", "Bannana");
        myMultimap.put("Fruits", "Apple");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Vegetables", "Carrot");

        // Getting the size
        int size = myMultimap.size();
        System.out.println(size);
        // 5

        // Getting values
        Collection<String> fruits = myMultimap.get("Fruits");
        System.out.println(fruits);
        //  [Bannana, Apple, Pear, Pear]
        System.out.println(ImmutableSet.copyOf(fruits));
        // [Bannana, Apple, Pear]
        // Set<Foo> set = Sets.newHashSet(list);
        // Set<Foo> foo = new HashSet<Foo>(myList);

        Collection<String> vegetables = myMultimap.get("Vegetables");
        System.out.println(vegetables);
        // [Carrot]

        // Iterating over entire Mutlimap
        for (String value : myMultimap.values()) {
            System.out.println(value);
        }

        // Removing a single value
        myMultimap.remove("Fruits", "Pear");
        System.out.println(myMultimap.get("Fruits"));
        // [Bannana, Apple, Pear]

        // Remove all values for a key
        myMultimap.removeAll("Fruits");
        System.out.println(myMultimap.get("Fruits"));
        // [] (Empty Collection!)

        Map<String, String> map = new HashMap<>();
        map.put("zhang", "san");
        Set<String> keys = map.keySet();
        map.compute("zhang", (k, v) -> keys.contains(k) ? v + "..." : k);
        System.out.println(map);
    }
}
