package cn.stt.test.stub;

import cn.stt.test.service.TestService;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/9/14.
 */
public class TestServiceStub implements TestService {
    private final TestService testService;

    //构造函数传入真正的远程代理对象
    public TestServiceStub(TestService testService){
        this.testService = testService;
    }

    @Override
    public String get() {
        // 此代码在客户端执行
        System.out.println("hehe");
        // 你可以在客户端做ThreadLocal本地缓存，或预先验证参数是否合法，等等
        try {
            return testService.get();
        } catch (Exception e) {
            // 你可以容错，可以做任何AOP拦截事项
            e.printStackTrace();
            return "容错数据";
        }
    }
}
