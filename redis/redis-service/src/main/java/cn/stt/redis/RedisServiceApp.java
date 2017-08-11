package cn.stt.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/***
 * 启动类
 */
public class RedisServiceApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisServiceApp.class);

    public static void main(String[] args) {
        /*if (StringUtils.isEmpty(System.getProperty("spring.profiles.active"))) {
            System.setProperty("spring.profiles.active", "dev");
        }
        LOGGER.info("ENV={}", System.getProperty("spring.profiles.active"));*/
        LOGGER.info("redis service start success");
        com.alibaba.dubbo.container.Main.main(args);
    }
}

