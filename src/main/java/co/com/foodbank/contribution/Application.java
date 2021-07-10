package co.com.foodbank.contribution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"co.com.foodbank.contribution",
        "co.com.foodbank.contribution.restcontroller",
        "co.com.foodbank.contribution.v1.controller",
        "co.com.foodbank.contribution.service",
        "co.com.foodbank.contribution.config",
        "co.com.foodbank.contribution.v1.model",
        "co.com.foodbank.contribution.repository",
        "co.com.foodbank.contribution.exception"})
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
