package com.shanzhu.sc;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.shanzhu.sc.mapper")
public class BackendApplication {

  public static void main(String[] args) {
    //SpringBoot 执行启动
    SpringApplication.run(BackendApplication.class, args);

    log.info("=====================项目后端启动成功============================");
  }

}