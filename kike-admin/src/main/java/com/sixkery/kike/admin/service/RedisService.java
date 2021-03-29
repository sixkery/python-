package com.sixkery.kike.admin.service;

/**
 * @author sixkery
 * @date 2020/4/16
 * <p>
 * redis 操作 Service
 * 对象和数组都以 json 形式存储
 */
public interface RedisService {
    /**
     * 存储数据
     *
     * @param key   key
     * @param value value
     */

    /**
     * 根据key获取数据
     *
     * @param key key
     * @return 获取的数据
     */
    String get(String key);

    /**
     * 设置超时时间
     *
     * @param key    为那个 key 设置
     * @param expire 超时时间
     * @return boolean 值
     */
    boolean expire(String key, Long expire);

    void remove(String key);

    /**
     * 自增操作
     *
     * @param key   key
     * @param delta 自增步长
     * @return 自增步长
     */
    Long increment(String key, long delta);


}
