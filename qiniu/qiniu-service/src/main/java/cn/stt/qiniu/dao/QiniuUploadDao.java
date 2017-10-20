package cn.stt.qiniu.dao;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/10/13.
 */
@Repository
public class QiniuUploadDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuUploadDao.class);

    @Value("${accessKey}")
    private String accessKey;
    @Value("${secretKey}")
    private String secretKey;
    @Value("${bucket}")
    private String bucket;
    @Value("${domain}")
    private String domain;

    private static final UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone0()));
    private Auth auth = null;

    /**
     * 上传文件
     *
     * @param file
     * @param fileName
     * @return
     * @throws QiniuException
     */
    public String uploadFile(File file, String fileName) throws QiniuException {
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        ///生成上传凭证，然后准备上传
        Auth auth = getAuth();
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(file, key, upToken);
            return processResponse(response);
        } catch (QiniuException ex) {
            Response r = ex.response;
            LOGGER.info("文件上传异常:r={}", r);
            try {
                LOGGER.info("异常信息:body={}", r.bodyString());
            } catch (QiniuException ex2) {
//                ignore
            }
            throw ex;
        }
    }

    private Auth getAuth() {
        if (auth == null) {
            synchronized (QiniuUploadDao.class) {
                if (auth == null) {
                    auth = Auth.create(accessKey, secretKey);
                }
            }
        }
        return auth;
    }

    private String processResponse(Response response) throws QiniuException {
        LOGGER.info("response={}", response);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        LOGGER.info("文件上传成功:key={},hash={}", putRet.key, putRet.hash);
        String url = domain + putRet.key;
        LOGGER.info("文件上传成功:链接url={}", url);
        return url;
    }

}
