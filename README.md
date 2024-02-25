![veterinary](/static/logo.png)

# Veterinary Management System

This is a Spring Boot application for managing a veterinary clinic's operations. The system provides APIs for managing veterinarians, customers, animals, vaccinations, and appointments.

## Technologies

[![Java](https://img.shields.io/badge/Java-21.09-brightgreen)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.4-brightgreen)](https://spring.io/projects/spring-boot)
[![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-2.5.4-brightgreen)](https://spring.io/projects/spring-data-jpa)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16.0-brightgreen)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-3.8.2-brightgreen)](https://maven.apache.org/)

## Features

- **Veterinarian Management**: CRUD operations for veterinarians, managing their working days.
- **Customer Management**: CRUD operations for customers and their associated animals.
- **Vaccination Management**: CRUD operations for vaccinations given to animals, with validation for vaccine protection dates.
- **Appointment Management**: Creating, updating, viewing, and deleting appointments for animal vaccinations and check-ups.
- **Layered Architecture**: The application is developed following a layered architecture pattern.
- **Dependency Injection**: Constructor injection is used for Inversion of Control (IoC) and Dependency Injection (DI).
- **Exception Handling**: Custom exceptions are used for error handling, ensuring meaningful responses to API users.
- **Data Transfer Objects (DTOs)**: Request and response DTOs are used for API endpoints.
- **Database**: PostgreSQL is used as the relational database, with Spring Data JPA for data access.
- **API Documentation**: API endpoints are documented for easy integration and usage.
- **Sample Data**: Sample data is provided in the database for testing and demonstration purposes.

## Technologies Used

- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## Running the Application

1. Ensure you have JDK 11 or later installed.
2. Clone this repository.
3. Configure the PostgreSQL database in `application.properties`.
4. Build the project using Maven: `mvn clean install`.
5. Run the application: `mvn spring-boot:run`.

## API Endpoints

- **Veterinarians**:
  - `GET /api/veterinarians`: Get all veterinarians.
  - `POST /api/veterinarians`: Create a new veterinarian.
  - `GET /api/veterinarians/{id}`: Get details of a veterinarian by ID.
  - `PUT /api/veterinarians/{id}`: Update details of a veterinarian.
  - `DELETE /api/veterinarians/{id}`: Delete a veterinarian by ID.
  - `GET /api/veterinarians/{id}/working-days`: Get all working days of a veterinarian by ID.
  - `POST /api/veterinarians/{id}/working-days`: Add a working day for a veterinarian by ID.
  - `DELETE /api/veterinarians/{id}/working-days/{day}`: Delete a working day for a veterinarian by ID.

- **Customers**:
  - `GET /api/customers`: Get all customers.
  - `POST /api/customers`: Create a new customer.
  - `GET /api/customers/{id}`: Get details of a customer by ID.
  - `PUT /api/customers/{id}`: Update details of a customer.
  - `DELETE /api/customers/{id}`: Delete a customer by ID.
  - `GET /api/customers/{id}/animals`: Get all animals of a customer by ID.

- **Animals**:
  - `GET /api/animals`: Get all animals.
  - `POST /api/animals`: Create a new animal.
  - `GET /api/animals/{id}`: Get details of an animal by ID.
  - `PUT /api/animals/{id}`: Update details of an animal.
  - `DELETE /api/animals/{id}`: Delete an animal by ID.
  - `GET /api/animals/{id}/vaccinations`: Get all vaccinations of an animal by ID.
  - `POST /api/animals/{id}/vaccinations`: Add a vaccination for an animal by ID. **Check for vaccine protection date**.

- **Vaccinations**:
  - `GET /api/vaccinations`: Get all vaccinations.
  - `POST /api/vaccinations`: Create a new vaccination.
  - `GET /api/vaccinations/{id}`: Get details of a vaccination by ID.
  - `PUT /api/vaccinations/{id}`: Update details of a vaccination.
  - `DELETE /api/vaccinations/{id}`: Delete a vaccination by ID.

- **Appointments**:
  - `GET /api/appointments`: Get all appointments.
  - `POST /api/appointments`: Create a new appointment.
  - `GET /api/appointments/{id}`: Get details of an appointment by ID.
  - `PUT /api/appointments/{id}`: Update details of an appointment.
  - `DELETE /api/appointments/{id}`: Delete an appointment by ID.

- **Search**:
  - `GET /api/search/veterinarians`: Search veterinarians by name.
  - `GET /api/search/customers`: Search customers by name.
  - `GET /api/search/animals`: Search animals by name.
  - `GET /api/search/vaccinations`: Search vaccinations by name.
  - `GET /api/search/appointments`: Search appointments by date, veterinarian.

## UML Diagram

The UML diagram below shows the class diagram of the Veterinary Management System.

![UML Diagram](/static/uml.jpeg)

## Database Tables

The application uses the following tables in the PostgreSQL database:

- **veterinarians**: Stores veterinarian details.
- **working_days**: Stores working days of veterinarians.
- **customers**: Stores customer details.
- **animals**: Stores animal details.
- **vaccinations**: Stores vaccination details.
- **appointments**: Stores appointment details.

The tables are related to each other using foreign keys for data integrity.

- **veterinarians** and **working_days**: One-to-Many relationship.
- **customers** and **animals**: One-to-Many relationship.
- **animals** and **vaccinations**: One-to-Many relationship.
- **appointments** and **vaccinations**: Many-to-One relationship.
- **appointments** and **veterinarians**: Many-to-One relationship.
- **appointments** and **animals**: Many-to-One relationship.

## Sample Data

The application provides sample data for veterinarians, customers, animals, vaccinations, and appointments. The sample data is loaded into the database on application startup using the `data.sql` file.

## Conclusion

The Veterinary Management System is a Spring Boot application for managing a veterinary clinic's operations. The system provides APIs for managing veterinarians, customers, animals, vaccinations, and appointments. The application is developed following a layered architecture pattern, with PostgreSQL as the database and Spring Data JPA for data access. The API endpoints are documented for easy integration and usage.

## License

This project has no license. Feel free to use, modify, and distribute the code.
