package com.tasks.octotasks2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ExampleConfiguration {
    @Bean
    public ExampleBean exampleBean() {
        return new ExampleBean();
    }
}
