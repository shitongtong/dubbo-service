package cn.stt.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"/META-INF/spring/*.xml"})//,"/logback.xml"
public class RedisTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private JedisPool jedisPool;

    private Jedis jedis;

    @Before
    public void setJedis() {
        jedis = jedisPool.getResource();
    }

    @After
    public void closeJedis() {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * jedis 操作字符串
     */
    @Test
    public void testString() {
//        Logger.getGlobal().info(jedis.getDB() + "");
        // 增
        jedis.set("gao", "tian");// 每次都会覆盖旧的key <gao,tian>
        jedis.append("gao", "yue");// 追加,<gao,tianyue>
        jedis.mset("a", "A", "b", "B");// 多个增加
        // 删
        jedis.del("gao");
        // 查
//        Logger.getGlobal().info(jedis.get("a"));
        // 计数
//        Logger.getGlobal().info("" + jedis.decr("count"));
//        Logger.getGlobal().info("" + jedis.incr("num"));
    }

    /**
     * jedis 操作Set
     */
    @Test
    public void testSet() {
        // 删
        jedis.del("set0");
        //增加
        jedis.sadd("set0", "a");
        jedis.sadd("set0", "b");
        jedis.sadd("set0", "c");
        jedis.sadd("set0", "a");
        jedis.sadd("set0", "d");
        jedis.sadd("set0", "e");
        jedis.sadd("set0", "f");
//        Logger.getGlobal().info("" + jedis.smembers("set0"));//取出所有的set [b, c, a, d, e, f]
//        Logger.getGlobal().info("" + jedis.smembers("set0"));//取出所有的set [b, c, a, d, e, f]
        // 删除某个
        jedis.srem("set0", "e", "f");
        // 取
//        Logger.getGlobal().info("" + jedis.sismember("set0", "a"));//是否在set中
//        Logger.getGlobal().info("" + jedis.scard("set0"));//所有的set的个数   4
//        Logger.getGlobal().info("" + jedis.smembers("set0"));//取出所有的set [d, b, c, a]
//        Logger.getGlobal().info("" + jedis.smembers("set0"));//取出所有的set [d, b, c, a]
//        Logger.getGlobal().info("" + jedis.srandmember("set0", 2));//随机取出2个值
    }

    /**
     * jedis 操作List
     */
    @Test
    public void testList() {

        // 增加，后来的放在第一个
        jedis.lpush("list0", "gao");
        jedis.lpush("list0", "tian");
        jedis.lpush("list0", "yue");
        // 删
        jedis.del("list0");
        // 增加，后来的放到最后一个
        jedis.rpush("list0", "1");
        jedis.rpush("list0", "3");
        jedis.rpush("list0", "2");
        // 取
        List<String> all = jedis.lrange("list0", 0, -1);
//        Logger.getGlobal().info("" + all);
        //只有当list的值都是数字时，才能排序，而且数据库中的原来的list顺序不变
//        Logger.getGlobal().info("" + jedis.sort("list0"));
//        Logger.getGlobal().info("" + jedis.lrange("list0", 0, -1));
    }

    /**
     * jedis 操作map
     */
    @Test
    public void testMap() {
        // 存
        Map<String, String> map = new HashMap<>();
        map.put("a0", "A0");
        map.put("a1", "A1");
        map.put("a2", "A2");
        jedis.hmset("map0", map);// 多次执行，不会覆盖上一个key下的map,而是相当于对数据库中的一个map做了操作
        // jedis.del("map0");//删掉整个map
        // 删除map中某个键的值
        jedis.hdel("map0", "a2");
        // 取
        List<String> list = jedis.hmget("map0", "a0", "a1", "a2", "a3");// 如果map不存在也不会报错而是返回[null,  null, null, null]

//        Logger.getGlobal().info("" + list);// [A0, A1, null, null]
//        Logger.getGlobal().info("" + jedis.hmget("map0", "a0"));    //[A0]
//        Logger.getGlobal().info("" + jedis.hexists("map0", "a0"));// 判断此map是否有这个键 true
//        Logger.getGlobal().info("" + jedis.hlen("map0"));// key存在的个数 2
//        Logger.getGlobal().info("" + jedis.hkeys("map0"));// 返回所有的key [a1, a0]
//        Logger.getGlobal().info("" + jedis.hvals("map0"));// 返回所有的value [A0, A1]

        // 输出所有键值
        /*jedis.hkeys("map0").forEach(key ->
            Logger.getGlobal().info(key + "=" + jedis.hmget("map0", key))//a1=[A1] a0=[A0]
        );*/

    }

    /**
     * 地图api
     * http://cnodejs.org/topic/57328c4afc18ed8c0cb16026
     */
    @Test
    public void testGeo() {
        //地图api
        jedis.geoadd("aa", 100.33, 80.33, "tt1");
        jedis.geoadd("aa", 50.12, 30.53, "tt2");
        Double geodist = jedis.geodist("aa", "tt1", "tt2");
        java.util.logging.Logger.getGlobal().info("geodist=" + geodist);
        List<String> geohash = jedis.geohash("aa", "tt1", "tt2");
//        LOGGER.info("geohash={}", geohash);
        java.util.logging.Logger.getGlobal().info("geohash=" + geohash);
        geohash.stream().forEach(s -> {
//            LOGGER.info("s={}", s);
            java.util.logging.Logger.getGlobal().info("s=" + s);
        });
    }
}
