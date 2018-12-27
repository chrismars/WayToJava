package miao.videoquestionbank.service;
import java.io.IOException;
import java.io.InputStream;

public interface OSSService {
    void saveDocument(InputStream inputStream, String key)throws IOException;

    String getUrlFromKey(String key);

}
