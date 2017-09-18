package cn.stt.test.controller;

import cn.stt.test.po.Person;
import cn.stt.test.service.Notify;
import cn.stt.test.service.NotifyService;
import cn.stt.test.service.TestService;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.EchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

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
    private TestService test1Service;
    @Autowired
    private TestService test2Service;

    /**
     * 分组测试
     */
    @RequestMapping("/test")
    public void test() {
        // 隐式传参，后面的远程调用都会隐式将这些参数发送到服务器端，类似cookie，用于框架集成，不建议常规业务使用
        RpcContext.getContext().setAttachment("index", "1");
        LOGGER.info("test1Service.get()={}", test1Service.get());
        LOGGER.info("test2Service.get()={}", test2Service.get());
    }

    /**
     * 回声测试
     */
    @RequestMapping("/echo")
    public void echo() {
        EchoService echoService = (EchoService) this.test1Service;
        String ok = (String) echoService.$echo("ok");
        assert (ok.equals("ok"));
        System.out.println(ok);
    }

    @Autowired
    private NotifyService notifyService;
    @Autowired
    private Notify notify;

    /**
     * 测试事件通知
     *
     * @throws Exception
     */
    @RequestMapping("/notify")
    public void notifyTest() throws Exception {
        int requestId = 2;
        Person ret = notifyService.get(requestId);
//        LOGGER.info("ret={}", ret);
        System.out.println(ret);
        //for Test：只是用来说明callback正常被调用，业务具体实现自行决定.
        for (int i = 0; i < 10; i++) {
            if (!notify.ret.containsKey(requestId)) {
                System.out.println("no containsKey");
                Thread.sleep(200);
            } else {
                System.out.println("containsKey");
                break;
            }
        }
//        LOGGER.info("notify.ret.get(requestId).getId()={}", notify.ret.get(requestId).getId());
        System.out.println(notify.ret.get(requestId).getId());

    }

    @Autowired
    private TestService testService;

    /**
     * 测试本地存根stub
     */
    @RequestMapping("/stub")
    public void stub() {
        System.out.println(testService.get());
    }

    private boolean flag = true;
    private Map<String, String> map = new ConcurrentHashMap<>();

    private String get(String key) {
        return map.get(key);
    }

    private void add(String key, String value) throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        map.put(key, value);
    }

    @RequestMapping("/volatile")
    public void volatileTest(String v) throws InterruptedException {
        System.out.println("====flag=" + flag);
        if (flag) {
            flag = false;
            String key = "asd";
            String value = get(key);
            if (StringUtils.isBlank(value)) {
                add(key, v);
            }
            flag = true;
        }

        System.out.println("||| flag=" + flag);
        System.out.println("map.value=" + map.get("asd"));
        System.out.println("=========================================");
    }
}
