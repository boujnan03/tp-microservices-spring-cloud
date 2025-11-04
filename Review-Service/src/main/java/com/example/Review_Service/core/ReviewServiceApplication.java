package com.example.Review_Service.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("com.rachid.Review_Service.Entity")
@EnableJpaRepositories("com.rachid.Review_Service.Dao")
@ComponentScan({"com.rachid.Review_Service.Services", "com.rachid.Review_Service.Controllers"})
public class ReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewServiceApplication.class, args);
	}

}
