package cn.stt.mail.service;

import cn.stt.mail.dao.MailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/10.
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    MailDao mailDao;

    @Override
    public void sendSimpleMessage() {
        mailDao.sendSimpleMessage("","","","");
    }
}
