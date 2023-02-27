package com.tasks.octotasks2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Octotasks2Application {

	public static void main(String[] args) {
		SpringApplication.run(Octotasks2Application.class, args);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(ExampleConfiguration.class);
		context.refresh();
		ExampleBean exampleBean=context.getBean(ExampleBean.class);
		context.close();
	}

}
