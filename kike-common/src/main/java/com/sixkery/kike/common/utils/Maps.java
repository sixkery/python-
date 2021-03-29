package com.sixkery.kike.common.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author maoning
 */
public final class Maps {

    /**
     * 创建一个赋了初始值的HashMap
     * @param key
     * @param value
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> HashMap<K, V> newHashMap(K key, V value) {
        HashMap<K, V> instance = new HashMap<>();
        instance.put(key, value);
        return instance;
    }

    /**
     * 返回一个Map构建器
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> MapBuilder<K, V> builder() {
        return new MapBuilder<K, V>();
    }

    /**
     * Map建造器
     * @param <K>
     * @param <V>
     */
    public static class MapBuilder<K, V> {

        private Map<K, V> map = Collections.emptyMap();

        public MapBuilder<K, V> put(K key, V value) {
            this.map.put(key, value);
            return this;
        }

        public Map<K, V> build() {
            return this.map;
        }
    }

}
