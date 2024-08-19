package com.kcc.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestfulApplication {

    public static void main(String[] args) {
        System.out.println("[KCC SpringBoot] :: started");
        SpringApplication.run(RestfulApplication.class, args);
    }

    @Bean
    public SessionLocaleResolver localResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA);

        return localeResolver;
    }

}
