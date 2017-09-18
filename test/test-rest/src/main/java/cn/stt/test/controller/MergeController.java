package cn.stt.test.controller;

import cn.stt.test.service.MergeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分组聚合测试
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/9/12.
 */
@RestController
@RequestMapping("/merge")
public class MergeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private MergeService mergeService;
    @Autowired
    private MergeService mergeService23;

    @RequestMapping("/")
    public void merge() {
        List<String> list = mergeService.mergeResult();
        LOGGER.info(list.toString());
    }

    @RequestMapping("/23")
    public void merge23() {
        List<String> list = mergeService23.mergeResult();
        LOGGER.info(list.toString());
    }
}
