package cn.stt.redis.service;

import cn.stt.redis.dao.RedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"/META-INF/spring/*.xml"})
public class RedisServiceImplTest {

    @Autowired
    RedisDao redisDao;

    private String key = "asd";
    private String value = "123456";

    @Test
    public void incr() throws Exception {

    }

    @Test
    public void decr() throws Exception {

    }

    @Test
    public void set() throws Exception {
        redisDao.set(key,value);
    }

    @Test
    public void set1() throws Exception {

    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void exists() throws Exception {

    }

    @Test
    public void rename() throws Exception {

    }

    @Test
    public void expire() throws Exception {

    }

    @Test
    public void expire1() throws Exception {

    }

    @Test
    public void del() throws Exception {

    }

}