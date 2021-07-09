package com.test.mark.zhang.test.other.project.org;

import java.util.ArrayList;
import java.util.HashMap;

public class MyMap<K, V> {
    private HashMap<K, ArrayList<V>> data = new HashMap<>();

    //添加的时候不会被覆盖
    public boolean add(K k, V v) {
        if (data.containsKey(k)) {
            data.get(k).add(v);
        } else {
            ArrayList<V> list = new ArrayList<>();
            list.add(v);
            data.put(k, list);
        }
        return true;
    }

    public HashMap<K, ArrayList<V>> getData() {
        return data;
    }
}
