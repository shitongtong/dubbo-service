package cn.stt.redis.service;

import cn.stt.redis.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/10.
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisDao redisDao;

    @Override
    public long incr(String key) {
        return redisDao.incr(key);
    }

    @Override
    public long decr(String key) {
        return redisDao.decr(key);
    }

    /**
     * 添加记录,如果记录已存在将覆盖原有的value
     * 默认过期时间7天
     *
     * @param key
     * @param value
     * @return 状态码
     */
    @Override
    public String set(String key, String value) {
        String status = redisDao.set(key, value);
        return status;
    }

    @Override
    public String set(String key, String value, int second) {
        return redisDao.set(key, value, second);
    }

    @Override
    public String get(String key) {
        return redisDao.get(key);
    }

    @Override
    public boolean exists(String key) {
        return redisDao.exists(key);
    }

    @Override
    public String rename(String oldKey, String newKey) {
        return redisDao.rename(oldKey, newKey);
    }

    @Override
    public void expire(String key) {
        redisDao.expire(key);
    }

    @Override
    public void expire(String key, int seconds) {
        redisDao.expire(key, seconds);
    }

    @Override
    public long del(String... keys) {
        return redisDao.del(keys);
    }
}
