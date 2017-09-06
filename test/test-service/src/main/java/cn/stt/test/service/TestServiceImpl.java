package cn.stt.test.service;

import org.springframework.stereotype.Service;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/22.
 */
@Service
public class TestServiceImpl implements TestService{
    @Override
    public String get() {
        return "TestServiceImpl";
    }
}
