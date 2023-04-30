package org.bristolenergynetwork.retrofit.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.RandomUtil;
import com.aliyun.oss.OSSClient;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

  @Autowired OSSClient ossClient;

  @Autowired AliyunOSSConfig aliyunOSSConfig;

  @Override
  public String upload(MultipartFile file) throws Exception {
    String fileName = buildFileName(file);
    ossClient.putObject(aliyunOSSConfig.getBucketName(), fileName, file.getInputStream());
    return getUrl(fileName);
  }

  @Override
  public String upload(File file) {
    String fileName = buildFileName(file);
    ossClient.putObject(
        aliyunOSSConfig.getBucketName(), fileName, cn.hutool.core.io.FileUtil.getInputStream(file));
    return getUrl(fileName);
  }

  @Override
  public String upload(InputStream inputStream, String originalFileName) {
    String fileName = buildFileName(originalFileName);
    ossClient.putObject(aliyunOSSConfig.getBucketName(), fileName, inputStream);
    return getUrl(fileName);
  }

  @Override
  public String upload(byte[] bytes, String originalFileName) throws Exception {
    String fileName = buildFileName(originalFileName);
    ossClient.putObject(aliyunOSSConfig.getBucketName(), fileName, new ByteArrayInputStream(bytes));
    return getUrl(fileName);
  }

  private String buildFileName(String originalFileName) {
    String fileName = RandomUtil.randomString(48) + originalFileName;
    String datePath = new DateTime().toString("yyyy/MM/dd");
    fileName = datePath + "/" + fileName;
    return fileName;
  }

  public String getUrl(String key) {
    return new StringBuilder(aliyunOSSConfig.getProtocol())
        .append("://")
        .append(aliyunOSSConfig.getBucketName())
        .append(".")
        .append(aliyunOSSConfig.getEndpoint())
        .append("/")
        .append(key)
        .toString();
  }

  private String buildFileName(MultipartFile file) {
    String fileName = file.getOriginalFilename();

    String uuid = RandomUtil.randomString(48);
    fileName = uuid + fileName;

    String datePath = new DateTime().toString("yyyy/MM/dd");
    // 拼接
    fileName = datePath + "/" + fileName;
    return fileName;
  }

  private String buildFileName(File file) {
    String fileName = file.getName();

    String uuid = RandomUtil.randomString(48);
    fileName = uuid + fileName;

    String datePath = new DateTime().toString("yyyy/MM/dd");
    fileName = datePath + "/" + fileName;
    return fileName;
  }
}
