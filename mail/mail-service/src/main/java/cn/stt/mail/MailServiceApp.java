package cn.stt.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/***
 * 启动类
 */
public class MailServiceApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceApp.class);

    public static void main(String[] args) {
        LOGGER.info("mail service start success");
        com.alibaba.dubbo.container.Main.main(args);
    }
}

