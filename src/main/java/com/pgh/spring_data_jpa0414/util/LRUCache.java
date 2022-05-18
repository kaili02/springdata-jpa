package com.pgh.spring_data_jpa0414.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author kevin
 * @version 1.0
 * @date 2022/4/22
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache<Object, Object> cache = new LRUCache<>(3);
        cache.put("1", "a");
        cache.put("2", "b");
        cache.put("3", "d");
        System.out.println(cache.keySet());
        cache.put("4", "d");
        System.out.println(cache.keySet());
        cache.put("3", "d");
        System.out.println(cache.keySet());
        cache.put("3", "d");
        System.out.println(cache.keySet());
        cache.get("2");
        System.out.println(cache.keySet());
    }
}
