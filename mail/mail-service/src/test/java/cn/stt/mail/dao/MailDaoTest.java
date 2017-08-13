package cn.stt.mail.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by stt on 2017/8/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"/META-INF/spring/*.xml"})//,"/logback.xml"
public class MailDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailDaoTest.class);


    @Autowired
    MailDao mailDao;

    @Test
    public void sendSimpleMessage() throws Exception {
        String subject = "springmail simple message";
        String text = "springmail simple message<br/>springmail simple message";
        String from = "767035687@qq.com";
        String to = "1249655484@qq.com";
        mailDao.sendSimpleMessage(subject, text, from, to);
        LOGGER.info("发送成功！");
    }

}