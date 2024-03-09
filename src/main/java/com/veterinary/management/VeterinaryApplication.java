package com.veterinary.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@OpenAPIDefinition(
		info = @io.swagger.v3.oas.annotations.info.Info(
				title = "Veterinary API",
				version = "1.0",
				description = "This is a Spring Boot application for managing a veterinary clinic's operations. The system provides APIs for managing doctors, customers, animals, vaccines, and appointments. The application is developed following a layered architecture pattern, with PostgreSQL as the database and Spring Data JPA for data access. The API endpoints are documented for easy integration and usage."
		)
)

@SpringBootApplication
public class VeterinaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeterinaryApplication.class, args);
	}

}
