package cn.stt.test.service;

import cn.stt.test.po.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/9/13.
 */
public interface Notify {
    Map<Integer, Person> ret = new HashMap<Integer, Person>();
    Map<Integer, Throwable> errors = new HashMap<Integer, Throwable>();
    void onreturn(Person msg, Integer id);
    void onthrow(Throwable ex, Integer id);
}
