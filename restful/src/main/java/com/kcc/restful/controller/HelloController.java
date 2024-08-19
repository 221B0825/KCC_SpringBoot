package com.kcc.restful.controller;

import com.kcc.restful.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

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
}
