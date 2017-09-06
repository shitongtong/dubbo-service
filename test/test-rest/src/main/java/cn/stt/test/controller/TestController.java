package cn.stt.test.controller;

import cn.stt.test.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/22.
 */
@RestController
@RequestMapping("/")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    TestService test1Service;
    @Autowired
    TestService test2Service;

    @RequestMapping("/test")
    public void test(){
        LOGGER.info("test1Service.get()={}",test1Service.get());
        LOGGER.info("test2Service.get()={}",test2Service.get());
    }
}
