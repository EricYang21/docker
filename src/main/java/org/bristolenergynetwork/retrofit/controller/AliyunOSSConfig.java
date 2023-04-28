package org.bristolenergynetwork.retrofit.controller;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.Protocol;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOSSConfig {
  @Value("${aliyun.oss.accessKeyId}")
  public String accessKeyId;

  @Value("${aliyun.oss.accessKeySecret}")
  public String accessKeySecret;

  @Value("${aliyun.oss.endpoint}")
  public String endpoint;

  @Value("${aliyun.oss.protocol}")
  public String protocol;

  @Value("${aliyun.oss.bucketName}")
  public String bucketName;

  //
  //    public static String END_POINT;
  //    public static String ACCESS_KEY_ID;
  //    public static String ACCESS_KEY_SECRET;
  //    public static String BUCKET_NAME;
  //    public static String PROTOCOL;
  @Bean
  @ConditionalOnProperty(name = "aliyun.oss.accessKeyId", matchIfMissing = true)
  public OSSClient ossClient() {
    CredentialsProvider credentialsProvider =
        new DefaultCredentialProvider(accessKeyId, accessKeySecret);

    ClientConfiguration clientConfiguration = new ClientBuilderConfiguration();
    if (Protocol.HTTPS.toString().equalsIgnoreCase(protocol)) {
      clientConfiguration.setProtocol(Protocol.HTTPS);
    }

    OSSClient ossClient = new OSSClient(endpoint, credentialsProvider, clientConfiguration);
    return ossClient;
  }
}
