package com.kotbegemot.testtask1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main class of Newsline application
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.kotbegemot.testtask1.repository.jpa")
@EnableTransactionManagement
public class TestTask1Application {
    /**
     * Spring Boot autoconfig method
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TestTask1Application.class, args);
    }

}
