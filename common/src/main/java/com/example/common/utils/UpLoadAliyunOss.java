package com.example.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * author ye
 * createDate 2022/8/24  21:17
 */
public class UpLoadAliyunOss {
    public static String upload(MultipartFile file) {
        String endpoint = "oss-cn-guangzhou.aliyuncs.com";
        String buckerName = "zhangyuyetypora";
        String OSSAccessKeyId = "LTAI5tCNy3mYdX9sUyZTFYHd";
        String OSSAccessKeySecret = "g65q2vga5iQup8sVB0aKxjUNcJD80r";
        String dir = "music/";
        OSS ossClient = new OSSClientBuilder().build(endpoint, OSSAccessKeyId, OSSAccessKeySecret);

        String fileName = file.getOriginalFilename();

        String musicURL = "https://"+ buckerName +"." + endpoint + "/" + dir + fileName;

        try {
            PutObjectResult putObjectResult = ossClient.putObject(buckerName, dir + fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ossClient.shutdown();

        return musicURL;
    }
}
