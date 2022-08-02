package com.example.contactmenagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class ContactMenagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactMenagmentApplication.class, args);
	}

}
