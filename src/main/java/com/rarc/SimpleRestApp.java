package com.rarc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SimpleRestApp extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(SimpleRestApp.class, args);
    }

}
