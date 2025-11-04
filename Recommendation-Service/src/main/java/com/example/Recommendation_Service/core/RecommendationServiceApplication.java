package com.example.Recommendation_Service.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("com.example.Recommendation_Service.Entity")
@EnableJpaRepositories("com.example.Recommendation_Service.repository")
@ComponentScan({"com.example.Recommendation_Service.Services", "com.example.Recommendation_Service.controllers"})

public class RecommendationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommendationServiceApplication.class, args);
	}

}
