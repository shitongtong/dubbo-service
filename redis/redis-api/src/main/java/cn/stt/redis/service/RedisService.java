package cn.stt.redis.service;



/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/10.
 */
public interface RedisService {


    /**
     * 将key对应的value加1，只有value可以转为数字时该方法才可用
     *
     * @param key
     * @return long 加1后的值
     */
    long incr(String key);

    /**
     * 将key对应的value减1，只有value可以转为数字时该方法才可用
     *
     * @param key
     * @return long 减1后的值
     */
    long decr(String key);

    /**
     * 设置key和value
     *
     * @param key
     * @param value
     * @return
     */
    String set(String key, String value);

    /**
     * 设置key和value,并设置过期时间
     *
     * @param key
     * @param value
     * @param second 秒为单位
     * @return
     */
    String set(String key, String value, int second);

    /**
     * 根据key获取value
     *
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    boolean exists(String key);

    /**
     * 重命名key
     *
     * @param oldKey
     * @param newKey
     * @return 状态码
     */
    String rename(String oldKey, String newKey);

    /**
     * 为key设置默认过期时间：7天
     *
     * @param key
     * @return
     */
    void expire(String key);

    /**
     * 为key设置过期时间
     *
     * @param key
     * @param seconds
     * @return
     */
    void expire(String key, int seconds);

    /**
     * 删除keys对应的记录,可以是多个key
     *
     * @param keys
     * @return 删除的记录数
     */
    long del(String... keys);
}
