package com.example.Product_Service.Core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.rachid.Product_Service.Entity")
@EnableJpaRepositories("com.rachid.Product_Service.Dao")
@ComponentScan({"com.rachid.Product_Service.Services", "com.rachid.Product_Service.Controllers"})
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
