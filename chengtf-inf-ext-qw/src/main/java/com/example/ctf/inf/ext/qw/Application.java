package com.example.ctf.inf.ext.qw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: TODO
 * @author: chengtf
 * @date: 2024/3/11 0:00
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.example.ctf.inf.ext.qw")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
