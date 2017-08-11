package cn.stt.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/4/20.
 */
@Repository
public class RedisDao {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 过期时间：默认7天
     */
    private final int expire = 3600 * 24 * 7;


    /**
     * 从jedis连接池中获取获取jedis对象
     *
     * @return
     */
    private Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 关闭连接
     */
    private void closeJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param seconds
     */
    public void expire(String key, int seconds) {
        if (seconds <= 0) {
            return;
        }
        Jedis jedis = getJedis();
        jedis.expire(key, seconds);
        closeJedis(jedis);
    }

    /**
     * 设置默认过期时间
     *
     * @param key
     */
    public void expire(String key) {
        expire(key, expire);
    }

    /**
     * 将key对应的value加1，只有value可以转为数字时该方法才可用
     *
     * @param key
     * @return long 加1后的值
     */
    public long incr(String key) {
        Jedis jedis = getJedis();
        long len = jedis.incr(key);
        closeJedis(jedis);
        return len;
    }

    /**
     * 将key对应的value减1，只有value可以转为数字时该方法才可用
     *
     * @param key
     * @return long 减1后的值
     */
    public long decr(String key) {
        Jedis jedis = getJedis();
        long len = jedis.decr(key);
        closeJedis(jedis);
        return len;
    }

    /**
     * 添加记录,如果记录已存在将覆盖原有的value
     *
     * @param key
     * @param value
     * @return 状态码
     */
    public String set(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        String status = jedis.set(key, value);
        closeJedis(jedis);
        return status;
    }

    /**
     * 添加记录,如果记录已存在将覆盖原有的value
     * 默认过期时间7天
     *
     * @param key
     * @param value
     * @return 状态码
     */
    public String set(String key, String value) {
        String status = set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        expire(key);
        return status;
    }

    /**
     * 添加记录并设置过期时间,如果记录已存在将覆盖原有的value
     *
     * @param key
     * @param value
     * @param second 过期时间
     * @return 状态码
     */
    public String set(String key, String value, int second) {
        String status = set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        expire(key, second);
        return status;
    }

    /**
     * 根据key获取记录
     *
     * @param key
     * @return 值
     */
    public String get(String key) {
        Jedis jedis = getJedis();
        String value = jedis.get(key);
        closeJedis(jedis);
        return value;
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return boolean
     */
    public boolean exists(String key) {
        Jedis jedis = getJedis();
        boolean exis = jedis.exists(key);
        closeJedis(jedis);
        return exis;
    }

    /**
     * 更改key
     *
     * @param oldkey
     * @param newkey
     * @return 状态码
     */
    private String rename(byte[] oldkey, byte[] newkey) {
        Jedis jedis = getJedis();
        String status = jedis.rename(oldkey, newkey);
        closeJedis(jedis);
        return status;
    }

    /**
     * 更改key
     *
     * @param oldkey
     * @param newkey
     * @return 状态码
     */
    public String rename(String oldkey, String newkey) {
        return rename(SafeEncoder.encode(oldkey),
                SafeEncoder.encode(newkey));
    }

    /**
     * 删除keys对应的记录,可以是多个key
     *
     * @param keys
     * @return 删除的记录数
     */
    public long del(String... keys) {
        Jedis jedis = getJedis();
        long count = jedis.del(keys);
        closeJedis(jedis);
        return count;
    }

}
