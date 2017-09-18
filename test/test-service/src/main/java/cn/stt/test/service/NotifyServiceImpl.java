package cn.stt.test.service;

import cn.stt.test.po.Person;
import org.springframework.stereotype.Service;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/9/13.
 */
@Service
public class NotifyServiceImpl implements NotifyService {
    @Override
    public Person get(int id) {
        return new Person(id, "charles`son", 4);
    }
}
