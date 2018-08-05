package com.igor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ApplePricesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplePricesApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "Hello Spring Boot!";
    }
}