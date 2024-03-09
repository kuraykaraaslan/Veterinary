![veterinary](/static/logo.png)

# Veterinary Management System

This is a Spring Boot application for managing a veterinary clinic's operations. The system provides APIs for managing doctors, customers, animals, vaccines, and appointments. The application is developed following a layered architecture pattern, with PostgreSQL as the database and Spring Data JPA for data access. The API endpoints are documented for easy integration and usage.

## Technologies

[![Java](https://img.shields.io/badge/Java-17-brightgreen)](https://www.java.com/en/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16.0-brightgreen)](https://www.postgresql.org/)
[![Swagger](https://img.shields.io/badge/Swagger-3.0-brightgreen)](https://swagger.io/)

## Features

- **Doctor Management**: CRUD operations for doctors and their associated available days.
- **Customer Management**: CRUD operations for customers and their associated animals.
- **Animal Management**: CRUD operations for animals and their associated vaccinations and appointments.
- **Vaccine Management**: CRUD operations for vaccines.
- **Appointment Management**: Creating, updating, viewing, and deleting appointments for animal vaccinations and check-ups.

## Architecture
- **Layered Architecture**: The application is developed following a layered architecture pattern.
- **Dependency Injection**: Constructor injection is used for Inversion of Control (IoC) and Dependency Injection (DI).
- **Exception Handling**: Custom exceptions are used for error handling, ensuring meaningful responses to API users.
- **Data Transfer Objects (DTOs)**: Request and response DTOs are used for API endpoints.
- **Database**: Postgresql is used as the relational database, with Spring Data JPA for data access.
- **API Documentation**: API endpoints are documented for easy integration and usage in swagger.
- **Sample Data**: Sample data is provided in the database for testing and demonstration purposes.

## Running the Application

1. Ensure you have JDK 11 or later installed.
2. Clone this repository.
3. Configure the PostgreSQL database in `application.properties`.
4. Build the project using Maven: `mvn clean install`.
5. Run the application: `mvn spring-boot:run`.

## API Documentation

The API documentation is available at `/swagger-ui/index.html` with Swagger UI. The documentation provides details about the API endpoints, request and response bodies, and sample requests.

![Swagger](/static/docs.png)


## UML Diagram

The UML diagram below shows the class diagram of the Veterinary Management System.

![UML Diagram](/static/uml.png)


## Sample Data

The application provides sample data for testing and demonstration purposes. The sample data is loaded using the `data.sql` file.


## Conclusion

The Veterinary Management System is a Spring Boot application for managing a veterinary clinic's operations. The system provides APIs for managing doctors, customers, animals, vaccines, and appointments. The application is developed following a layered architecture pattern, with PostgreSQL as the database and Spring Data JPA for data access. The API endpoints are documented for easy integration and usage.

## License

This project has no license. Feel free to use, modify, and distribute the code.

