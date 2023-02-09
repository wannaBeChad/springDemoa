package com.example.springdemo;

import com.example.springdemo.entity.User;
import com.example.springdemo.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.springdemo.repository")
public class SpringDemoApplication {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {

        ConfigurableApplicationContext context=  SpringApplication.run(SpringDemoApplication.class, args);
        Object dataSource = context.getBean("dataSource");
System.out.println(dataSource);

    }
    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            userRepository.save(new User(1L,"John@adc.com","asd"));
        };
    }

}
