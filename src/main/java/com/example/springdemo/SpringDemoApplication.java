package com.example.springdemo;

import com.example.springdemo.entity.Book;
import com.example.springdemo.entity.User;
import com.example.springdemo.repository.BookRepository;
import com.example.springdemo.repository.UserRepository;
import com.example.springdemo.service.BookService;
import com.example.springdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.springdemo.repository")
public class SpringDemoApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;
    private BookService bookService;

    public SpringDemoApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context=  SpringApplication.run(SpringDemoApplication.class, args);
        Object dataSource = context.getBean("dataSource");
System.out.println(dataSource);

    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            userRepository.save(new User(1L,"John@adc.com","asd"));
            loadDataFromCsv("C:\\Users\\fung\\Downloads\\books.csv");
        };
    }

   private void loadDataFromCsv(String filePath){
       bookService.readAndInsertDataFromCSV(filePath);
   }


}
