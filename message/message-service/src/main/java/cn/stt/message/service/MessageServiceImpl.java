package cn.stt.message.service;

import cn.stt.message.dao.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/14.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public String sendMessage(String phone, String message) throws Exception {
        return messageDao.sendMessage(phone, message);
    }
}
