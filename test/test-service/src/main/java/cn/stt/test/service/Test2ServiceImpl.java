package cn.stt.test.service;

import com.alibaba.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/22.
 */
@Service
public class Test2ServiceImpl implements TestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test2ServiceImpl.class);

    @Override
    public String get() {
        // 获取客户端隐式传入的参数，用于框架集成，不建议常规业务使用
        String index = RpcContext.getContext().getAttachment("index");
        LOGGER.info("index={}", index);
        return "Test2ServiceImpl";
    }
}
