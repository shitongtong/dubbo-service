package cn.stt.mail.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        // "<html><head></head><body><h1>你好：附件中有学习资料！</h1></body></html>"
        String subject = "springmail simple message";
        String text = "springmail simple message</br>springmail simple message";
        String from = "xxxxxxxxx@qq.com";
        String to = "xxxxxxxxxxx@qq.com";
        String cc = "xxxxxxxxxxxxxx@163.com";
        String bcc = "";
        mailDao.sendSimpleMessage(subject, text, from, to, cc, bcc);
        LOGGER.info("邮件发送成功！");
    }

    @Test
    public void sendMessageWithImageAndAttached() throws Exception {
        String subject = "springmail sendMessageWithImageAndAttached";
        String text = "springmail 附件和图片：";
        String from = "xxxxxx@qq.com";
        String to = "xxxxxxx@qq.com";
        String cc = "xxxxxxxxxx@163.com";
        String bcc = "";
        String imgPath = "D:\\图片\\小幺鸡.png";
        String attachmentPath = "D:\\图片\\文档.doc";
        String attachmentName = "文档.doc";
        mailDao.sendMessageWithImageAndAttached(subject, text, imgPath, attachmentPath, attachmentName, from, to, cc, bcc);
        LOGGER.info("邮件发送成功！");
    }

}