package com.learning.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication()
@EnableJpaAuditing
public class LearningManagementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningManagementApiApplication.class, args);
    }

}
