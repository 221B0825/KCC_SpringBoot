package com.kcc.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestaurantApplication {

    public static void main(String[] args) {
        System.out.println("[KCC SpringBoot] :: restaurant application started");
        SpringApplication.run(RestaurantApplication.class, args);
    }
}
