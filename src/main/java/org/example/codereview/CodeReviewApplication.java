package org.example.codereview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CodeReviewApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodeReviewApplication.class, args);
    }
}
