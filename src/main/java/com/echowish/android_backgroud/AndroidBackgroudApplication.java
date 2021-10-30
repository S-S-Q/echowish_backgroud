package com.echowish.android_backgroud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("com.echowish.android_backgroud.dao")
public class AndroidBackgroudApplication {

    public static void main(String[] args) {
        SpringApplication.run(AndroidBackgroudApplication.class, args);
    }

}
