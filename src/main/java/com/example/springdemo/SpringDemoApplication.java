package com.example.springdemo;

import com.example.springdemo.entity.User;
import com.example.springdemo.repository.BookRepository;
import com.example.springdemo.repository.UserRepository;
import com.example.springdemo.service.BookService;
import com.example.springdemo.service.ViewService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BookRepository bookRepository;
    private BookService bookService;
    private ViewService viewService;

    public SpringDemoApplication(BookService bookService, ViewService viewService) {
        this.bookService = bookService;
        this.viewService = viewService;

    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context=  SpringApplication.run(SpringDemoApplication.class, args);
        Object dataSource = context.getBean("dataSource");
System.out.println(dataSource);

    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            userRepository.save(new User(1L,"test1@check24.de","asd"));
            userRepository.save(new User(2L,"test2@check24.de","asd"));
            userRepository.save(new User(3L,"test3@check24.de","asd"));
            loadBookDataFromCsv("C:\\Users\\fung\\Downloads\\books.csv");
            loadViewDataFromCsv("C:\\Users\\fung\\Downloads\\books-views.csv");
        };
    }

   private void loadBookDataFromCsv(String filePath){
       bookService.readAndInsertDataFromCSV(filePath);
   }

    private void loadViewDataFromCsv(String filePath){
        viewService.readAndInsertViewDataFromCSV(filePath);
   }


}
