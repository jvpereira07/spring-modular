package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication(scanBasePackages = "com.main")
@RestController
@EnableJpaAuditing
public class MainApplication {

  

  

  @GetMapping("/")
  public String home() {
    return "Hello from Main Application!";
  }

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }
}

