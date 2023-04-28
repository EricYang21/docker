package org.bristolenergynetwork.retrofit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"org.bristolenergynetwork.retrofit.mappers"})
// @ComponentScan("org.bristolenergynetwork.retrofit.datasource")
public class RetrofitApplication {
  public static void main(String[] args) {
    SpringApplication.run(RetrofitApplication.class, args);
  }
}
