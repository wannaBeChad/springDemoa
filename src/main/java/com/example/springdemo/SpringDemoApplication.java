package com.example.springdemo;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context=  SpringApplication.run(SpringDemoApplication.class, args);
        Object dataSource = context.getBean("dataSource");
System.out.println(dataSource);

    }

}
