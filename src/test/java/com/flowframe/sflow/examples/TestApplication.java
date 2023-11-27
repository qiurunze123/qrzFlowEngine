package com.flowframe.sflow.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qrz
 * @description
 * @date 2023/11/20 下午10:00
 * 山不向我走来，我便向它走去
 */
@SpringBootApplication(scanBasePackages={"com.flowframe.sflow"})
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}

