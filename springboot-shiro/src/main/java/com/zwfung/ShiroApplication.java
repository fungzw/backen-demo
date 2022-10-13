package com.zwfung;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zw fung
 */
@MapperScan("com.zwfung.mapper")
@SpringBootApplication
public class ShiroApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }
}
