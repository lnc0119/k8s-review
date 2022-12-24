package com.chloe.happydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication
@RestController
public class HappyDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HappyDemoApplication.class, args);
    }

    @GetMapping("happy-demo")
    public String MyHappyDemo(){
        Random random = new Random();
        return random.nextBoolean()? "It's Happy Day" : "Nono! Try again";
    }
}
