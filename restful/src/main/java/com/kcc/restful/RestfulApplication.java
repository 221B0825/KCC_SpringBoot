package com.kcc.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulApplication {

    public static void main(String[] args) {
        System.out.println("[KCC SpringBoot] :: started");
        SpringApplication.run(RestfulApplication.class, args);
    }

}
