package cn.stt.test.controller;

import cn.stt.test.po.Person;
import cn.stt.test.service.Notify;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/9/13.
 */
public class NotifyImpl implements Notify {
    @Override
    public void onreturn(Person msg, Integer id) {
        System.out.println("onreturn:" + msg);
        ret.put(id, msg);
    }

    @Override
    public void onthrow(Throwable ex, Integer id) {
        System.out.println("onthrow:" + ex);
        errors.put(id, ex);
    }
}
