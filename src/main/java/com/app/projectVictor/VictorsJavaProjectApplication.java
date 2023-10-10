package com.app.projectVictor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.app.projectVictor.Entities")
public class VictorsJavaProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(VictorsJavaProjectApplication.class, args);
    }

}
