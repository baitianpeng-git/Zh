package com.zh.btp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zh.btp.mapper")
public class ZhApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhApplication.class);
    }
}
