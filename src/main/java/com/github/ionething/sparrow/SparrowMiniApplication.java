package com.github.ionething.sparrow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SparrowMiniApplication {

    public static void main(String[] args) {
         SpringApplication.run(SparrowMiniApplication.class, args);
    }

}
