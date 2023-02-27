package com.tasks.octotasks2;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class ExampleBean {

    public ExampleBean(){
        System.out.println("Hello from ExampleBean constructor!");
    }

    @PostConstruct
    private void postconstructExampleBean() {
        System.out.println("Hello from postconstruct annotation!");
    }

    @PreDestroy
    private void predestroyExampleBean() {
        System.out.println("Hello from predestroy annotation!");
    }
}
