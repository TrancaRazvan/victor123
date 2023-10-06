package com.app.projectVictor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VictorsJavaProjectApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(VictorsJavaProjectApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
