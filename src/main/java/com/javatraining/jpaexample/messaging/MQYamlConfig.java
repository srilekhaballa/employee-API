package com.javatraining.jpaexample.messaging;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("rabbitmq")
public class MQYamlConfig {
    public String queue = "2032-queue1";
    public String exchange = "2032-topic1";
    public String router = "2032-router1";

    public MQYamlConfig(String queue, String exchange, String router) {
        super();
        this.queue = queue;
        this.exchange = exchange;
        this.router = router;
    }

    public MQYamlConfig() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }

    @Override
    public String toString() {
        return "MQYamlConfig [queue=" + queue + ", exchange=" + exchange + ", router=" + router + "]";
    }

}
