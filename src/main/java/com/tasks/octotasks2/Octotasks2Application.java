package com.tasks.octotasks2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Octotasks2Application {

	public static void main(String[] args) {
		SpringApplication.run(Octotasks2Application.class, args);
	}

}
