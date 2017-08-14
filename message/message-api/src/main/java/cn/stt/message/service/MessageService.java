package cn.stt.message.service;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/14.
 */
public interface MessageService {
    /**
     * 发送短信息
     *
     * @param phone   多个号码使用","分割
     * @param content 发送的内容
     * @return success 成功
     */
    String sendMessage(String phone, String content) throws Exception;
}
