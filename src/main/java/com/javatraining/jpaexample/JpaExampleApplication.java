package com.javatraining.jpaexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class JpaExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaExampleApplication.class, args);
    }

}
