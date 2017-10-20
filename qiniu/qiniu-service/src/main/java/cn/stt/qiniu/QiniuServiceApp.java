package cn.stt.qiniu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/***
 * 启动类
 */
public class QiniuServiceApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuServiceApp.class);

    public static void main(String[] args) {
        com.alibaba.dubbo.container.Main.main(args);
    }
}

