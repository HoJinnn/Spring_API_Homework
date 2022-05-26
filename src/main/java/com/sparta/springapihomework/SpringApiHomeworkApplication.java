package com.sparta.springapihomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringApiHomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringApiHomeworkApplication.class, args);
    }

}
