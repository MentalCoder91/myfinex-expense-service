package com.expleo.expense_service;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Expense microservice REST API Documentation",
				description = "MyFinEx Expense microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Anish Alwekar",
						email = "anish.alwekar@gmail.com",
						url = "https://github.com/MentalCoder91"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/MentalCoder91"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "MyFinEx Expense microservice REST API Documentation",
				url = "http://localhost:9093/swagger-ui/index.html"
		)
)
@EnableFeignClients
public class ExpenseServiceApplication  {


	public static void main(String[] args) {
		SpringApplication.run(ExpenseServiceApplication.class, args);
	}


}
