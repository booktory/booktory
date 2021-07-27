package com.ssafy.booktory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
public class BooktoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooktoryApplication.class, args);
	}

}
