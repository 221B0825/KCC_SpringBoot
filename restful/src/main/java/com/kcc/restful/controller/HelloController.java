package com.kcc.restful.controller;

import com.kcc.restful.bean.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean");
    }

    @GetMapping("/hello-world-bean/path/{name}")
    public HelloWorldBean helloWorldBeanPath(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World Bean: %s", name));
    }

    @GetMapping("/hello-world-international")
    public String helloWorldInternational(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }

}
