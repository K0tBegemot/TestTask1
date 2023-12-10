package com.kotbegemot.testtask1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableRedisRepositories(basePackages = "com.kotbegemot.testtask1.repository.redis")
@EnableJpaRepositories(basePackages = "com.kotbegemot.testtask1.repository.jpa")
@EnableTransactionManagement
public class TestTask1Application {

    public static void main(String[] args) {
        SpringApplication.run(TestTask1Application.class, args);
    }

}
