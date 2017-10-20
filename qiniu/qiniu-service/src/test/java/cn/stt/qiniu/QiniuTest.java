package cn.stt.qiniu;

import cn.stt.qiniu.dao.QiniuUploadDao;
import com.qiniu.common.QiniuException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/10/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"/META-INF/spring/application-context-core.xml"})
public class QiniuTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuTest.class);

    @Autowired
    private QiniuUploadDao qiniuUploadDao;

    @Test
    public void uploadFileTest() throws QiniuException {
        String filePath = "D:\\testoffice2pdf\\哈哈.pdf";
        filePath = "D:\\testoffice2pdf\\courseware\\image\\第3章第1节　钠及其重要化合物\\1.jpg";
        File file = new File(filePath);
        String url = qiniuUploadDao.uploadFile(file, file.getName());
        LOGGER.info("文件url={}", url);
    }
}
