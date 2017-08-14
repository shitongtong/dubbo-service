package cn.stt.mail.dao;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by stt on 2017/8/12.
 */
@Repository
public class MailDao {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    /**
     * 发送简单消息
     * 多收件人、抄送人、密送人
     *
     * @param subject
     * @param text
     * @param from
     * @param tos
     * @param ccs
     * @param bccs
     */
    public void sendSimpleMessage(String subject, String text, String from, String[] tos, String[] ccs, String[] bccs) {
        // 建立邮件消息
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setReplyTo(from);   //邮件回复人
        mailMessage.setTo(tos);
        if (ArrayUtils.isNotEmpty(ccs)) {
            mailMessage.setCc(ccs);
        }
        if (ArrayUtils.isNotEmpty(bccs)) {
            mailMessage.setBcc(bccs);
        }
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        // 发送邮件
        javaMailSender.send(mailMessage);
    }

    /**
     * 发送简单消息
     *
     * @param subject 主题
     * @param text    内容
     * @param from    发件人
     * @param to      收件人
     * @param cc      抄送人
     * @param bcc     密送人
     */
    public void sendSimpleMessage(String subject, String text, String from, String to, String cc, String bcc) {
        String[] ccs = null;
        if (StringUtils.isNotBlank(cc)) {
            ccs = new String[]{cc};
        }
        String[] bccs = null;
        if (StringUtils.isNotBlank(bcc)) {
            bccs = new String[]{bcc};
        }
        sendSimpleMessage(subject, text, from, new String[]{to}, ccs, bccs);

    }

    public void sendMessageWithImageAndAttached(String subject, String text, String imgPath, String attachmentPath, String attachmentName, String from, String to, String cc, String bcc) throws MessagingException {
        // 建立邮件消息,发送简单邮件和html邮件的区别
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        // 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
        // multipart模式 为true时发送附件 可以设置html格式
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");

        // 设置收件人，寄件人
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setCc(cc);
        messageHelper.setSubject(subject);
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head><body>");
        sb.append(text);
        if (StringUtils.isNotBlank(imgPath)) {
            sb.append("<img src='cid:imgcid'/>");

        }
        sb.append("</body></html>");
        System.out.println("StringBuilder==" + sb);
        // true 表示启动HTML格式的邮件
        messageHelper.setText(sb.toString(), true);
        if (StringUtils.isNotBlank(imgPath)) {
            //嵌入图片
            FileSystemResource img = new FileSystemResource(new File(imgPath));
            messageHelper.addInline("imgcid", img);
        }

        FileSystemResource file = new FileSystemResource(attachmentPath);
//        UrlResource urlResource = new UrlResource()
        // 这里的方法调用和插入图片是不同的。
        messageHelper.addAttachment(attachmentName, file);

        // 发送邮件
        javaMailSender.send(mailMessage);
    }

}
