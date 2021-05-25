package com.javatraining.jpaexample;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {JpaExampleApplication.class})
public class BaseClass {

    protected TestRestTemplate testRestTemplate = new TestRestTemplate();
}
