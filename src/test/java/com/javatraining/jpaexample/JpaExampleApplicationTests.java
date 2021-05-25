package com.javatraining.jpaexample;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {JpaExampleApplication.class})
public class JpaExampleApplicationTests {

    @Test
    void contextLoads() {}

}
