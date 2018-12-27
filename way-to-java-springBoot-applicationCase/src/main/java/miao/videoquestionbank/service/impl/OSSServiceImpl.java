package miao.videoquestionbank.service.impl;
import com.aliyun.oss.OSSClient;
import miao.videoquestionbank.service.OSSService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
public class OSSServiceImpl implements OSSService {
    @Value("${OSSClient.endpoint}")
    String endpoint;

    @Value("${OSSClient.accessKeyId}")
    String accessKetId;

    @Value("${OSSClient.accessKeySecret}")
    String accessKeySecret;

    @Value("${OSSClient.bucketName}")
    String bucketName;


    @Override
    public void saveDocument(InputStream inputStream, String key) {
        OSSClient ossClient=new OSSClient(endpoint,accessKetId,accessKeySecret);
        ossClient.putObject(bucketName,key,inputStream);
        ossClient.shutdown();
    }

    @Override
    public String getUrlFromKey(String key) {
        OSSClient ossClient=new OSSClient(endpoint,accessKetId,accessKeySecret);
        String ret = key;
        if (!key.startsWith("http")) {
            URL url = ossClient.generatePresignedUrl(bucketName, key, DateUtils.addHours(new Date(),1));
            ret = url.toString();
        }
        ossClient.shutdown();
        return ret;
    }
}
