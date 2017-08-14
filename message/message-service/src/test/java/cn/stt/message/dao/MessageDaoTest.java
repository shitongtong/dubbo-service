package cn.stt.message.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/*.xml")
public class MessageDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageDaoTest.class);

    @Autowired
    private MessageDao messageDao;

    @Test
    public void sendMessage() throws Exception {
        String phone = "13916593205";
        String content = "9527";
        String stateValue = messageDao.sendMessage(phone, content);
        LOGGER.info("stateValue={}", stateValue);
    }

}