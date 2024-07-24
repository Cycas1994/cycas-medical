package com.cycas.analyse.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xin.na
 * @since 2024/7/18 16:05
 */
@ComponentScan("com.cycas")
@SpringBootApplication
public class MedicalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalApplication.class, args);
    }

}
