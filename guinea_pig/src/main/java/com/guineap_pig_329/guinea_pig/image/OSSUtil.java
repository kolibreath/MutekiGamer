package com.guineap_pig_329.guinea_pig.image;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.guineap_pig_329.guinea_pig.util.Util;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class OSSUtil {



    private static String endpoint ;
    private static String accessKeyId ;
    private static String accessKeySecret;
    private static String bucketName ;

    public static String getEndpoint() {
        return endpoint;
    }

    public static void setEndpoint(String endpoint) {
        OSSUtil.endpoint = endpoint;
    }

    public static String getAccessKeyId() {
        return accessKeyId;
    }

    public static void setAccessKeyId(String accessKeyId) {
        OSSUtil.accessKeyId = accessKeyId;
    }

    public static String getAccessKeySecret() {
        return accessKeySecret;
    }

    public static void setAccessKeySecret(String accessKeySecret) {
        OSSUtil.accessKeySecret = accessKeySecret;
    }

    public static String getBucketName() {
        return bucketName;
    }

    public static void setBucketName(String bucketName) {
        OSSUtil.bucketName = bucketName;
    }

    public static OSSUtil getOssUtil() {
        return ossUtil;
    }

    public static void setOssUtil(OSSUtil ossUtil) {
        OSSUtil.ossUtil = ossUtil;
    }

    public static OSSUtil ossUtil;

    //获取OSSClient实例
    private static OSSClient getOSSClient(){
        endpoint = Util.CEN.getEndpoint();
        accessKeyId = Util.CEN.getAccessKeyId();
        accessKeySecret = Util.CEN.getAccessKeySecret();
        bucketName = Util.CEN.getBucketName();
        return new OSSClient(getEndpoint(), getAccessKeyId(), getAccessKeySecret());
    }

    //文件流上传
    public static void uploadFile(File file, String key ) throws FileNotFoundException {
        OSSClient ossClient = getOSSClient();
        // 上传文件流
        //InputStream inputStream = new FileInputStream(fileName);
        ossClient.putObject(getBucketName(), key , file);
        // 关闭client
        ossClient.shutdown();
    }

    // MultipartFile方式上传
    public static String uploadPic(MultipartFile file, String key) throws IOException {
        OSSClient ossClient = getOSSClient();
        ossClient.putObject(getBucketName(), key , file.getInputStream());
        ossClient.shutdown();
        return key;
    }

    // 删除文件
    public static void deleteFile(String key ){
        // 创建OSSClient实例
        OSSClient ossClient = getOSSClient();
        // 删除Object
        ossClient.deleteObject(getBucketName(), key);
        // 关闭client
        ossClient.shutdown();

    }

    //获取存储对象的名字
    public static void listObject(){
        OSSClient ossClient = getOSSClient();
        // 获取指定bucket下的所有Object信息
        ObjectListing listing = ossClient.listObjects(getBucketName());

        // 遍历所有Object
        for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
            System.out.println(objectSummary.getKey());
        }
    }

}
