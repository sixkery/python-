package com.sixkery.kike.admin.util;

import io.minio.*;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Minio 工具类
 *
 * @author sixkery
 * @date 2020/10/27
 */
@Data
@Component
public class MinioUtil {

    @Resource
    private MinioClient minioClient;

    /**
     * 检查存储桶是否存在
     *
     * @param bucketName 桶名称
     * @return 是否存在
     */
    @SneakyThrows
    public boolean bucketExist(String bucketName) {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 创建存储桶
     *
     * @param bucketName 桶名称
     * @return 是否创建成功
     */
    @SneakyThrows
    public boolean makeBucket(String bucketName) {
        if (!bucketExist(bucketName)) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            return true;
        } else {
            return false;
        }
    }

    /**
     * 文件上传
     *
     * @param bucketName    桶名称
     * @param multipartFile MultipartFile 是 spring 类型， HTML中 form data 方式上传的文件 + 文件名称
     * @param filename      文件名称
     */
    @SneakyThrows
    public void putObject(String bucketName, MultipartFile multipartFile, String filename) {
        PutObjectOptions putObjectOptions = new PutObjectOptions(multipartFile.getSize(),
                PutObjectOptions.MIN_MULTIPART_SIZE);

        putObjectOptions.setContentType(multipartFile.getContentType());
        minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(filename).stream(
                        multipartFile.getInputStream(), multipartFile.getSize(), -1)
                        .sse(putObjectOptions.sse())
                        .build());
    }

    /**
     * 文件访问路径
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return 文件访问路径
     */
    @SneakyThrows
    public String getObjectUrl(String bucketName, String objectName) {
        if (bucketExist(bucketName)) {
            return minioClient.getObjectUrl(bucketName, objectName);
        } else {
            return null;
        }

    }
}



