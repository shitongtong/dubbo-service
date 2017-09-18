package cn.stt.test.service;

import com.alibaba.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/22.
 */
@Service
public class Test1ServiceImpl implements TestService{
    private static final Logger LOGGER = LoggerFactory.getLogger(Test1ServiceImpl.class);

    @Autowired
    private TestService test2Service;

    @Override
    public String get() {
        RpcContext rpcContext = RpcContext.getContext();
        boolean isProviderSide = rpcContext.isProviderSide(); // 本端是否为提供端，这里会返回true
        String clientIP = rpcContext.getRemoteHost(); // 获取调用方IP地址
        String application = rpcContext.getUrl().getParameter("application"); // 获取当前服务配置信息，所有配置信息都将转换为URL的参数
        LOGGER.info("isProviderSide={}",isProviderSide);
        LOGGER.info("clientIP={}",clientIP);
        LOGGER.info("application={}",application);
        LOGGER.info("{}",test2Service.get());// 注意：每发起RPC调用，上下文状态会变化
        boolean isProviderSide1 = RpcContext.getContext().isProviderSide(); // 此时本端变成消费端，这里会返回false
        LOGGER.info("isProviderSide1={}",isProviderSide1);
        return "Test1ServiceImpl";
    }
}
