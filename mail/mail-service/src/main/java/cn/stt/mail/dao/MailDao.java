package cn.stt.mail.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Repository;

/**
 *
 * Created by stt on 2017/8/12.
 */
@Repository
public class MailDao {

    @Autowired
    MailSender mailSender;

    /**
     * 发送简单消息
     */
    public void sendSimpleMessage(String subject, String text, String from, String to) {

        // 建立邮件消息
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人 用数组发送多个邮件
        // String[] array = new String[] {"sun111@163.com","sun222@sohu.com"};
        // mailMessage.setTo(array);
        mailMessage.setFrom(from);
        mailMessage.setTo(to);

        mailMessage.setSubject(subject);
        mailMessage.setText(text);


        // 发送邮件
        mailSender.send(mailMessage);

    }

}
