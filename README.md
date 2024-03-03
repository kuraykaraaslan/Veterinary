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

- **Doctor Management**: CRUD operations for doctors and their associated available days.
- **Customer Management**: CRUD operations for customers and their associated animals.
- **Animal Management**: CRUD operations for animals and their associated vaccinations and appointments.
- **Vaccine Management**: CRUD operations for vaccines.
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

- **Doctors**
-- GET `/doctors`: Get all doctors
-- GET `/doctors/{id}`: Get doctor by id
-- POST `/doctors`: Create a new doctor
-- PUT `/doctors/{id}`: Update doctor
-- DELETE `/doctors/{id}`: Delete doctor



## UML Diagram

The UML diagram below shows the class diagram of the Veterinary Management System.

![UML Diagram](/static/uml.png)

## Database Tables

The application uses the following tables in the PostgreSQL database:

- **doctors**: Stores doctor details.
- **available_days**: Stores doctor's available days for consultation.
- **customers**: Stores customer details.
- **animals**: Stores animal details.
- **vaccines**: Stores vaccine details.
- **appointments**: Stores appointment details.

The tables are related to each other using foreign keys for data integrity.

- **doctors** has a one-to-many relationship with **available_days**.
- **customers** has a one-to-many relationship with **animals**.
- **animals** has a many-to-one relationship with **vaccines**.
- **animals** has a many-to-one relationship with **appointments**.

The database schema is created using the `schema.sql` file, and the sample data is loaded using the `data.sql` file.

## Request Body Templates

The following request body templates are used for creating and updating data in the application.

### Doctor
  
  ```json
  {
    "id": null,
    "name": "string",
    "phone": "string",
    "email": "string",
    "address": "string",
    "city": "string"
  }
  ```

### Animal

```json
{
  "id": null,
  "name": "string",
  "species": "string",
  "breed": "string",
  "gender": "string",
  "color": "string",
  "dateOfBirth": "yyyy-MM-dd",
  "customer": null
}
```

### Customer

```json
{
  "id": null,
  "name": "string",
  "phone": "string",
  "email": "string",
  "address": "string",
  "city": "string"
}

```

### Vaccine

```json
{
  "id": null,
  "name": "string",
  "code": "string",
  "protectionStartDate": "yyyy-MM-dd",
  "protectionFinishDate": "yyyy-MM-dd",
  "animal": null
}
```

### Appointment

```json
{
  "id": null,
  "date": "yyyy-MM-dd HH:mm",
  "doctorId": null,
  "animalId": null
}
```

### Available Day

```json
{
  "id": null,
  "availableDate": "yyyy-MM-dd",
  "vetId": null
}
```

## Sample Data

The application provides sample data for testing and demonstration purposes. The sample data is loaded using the `data.sql` file.


## Conclusion

The Veterinary Management System is a Spring Boot application for managing a veterinary clinic's operations. The system provides APIs for managing doctors, customers, animals, vaccines, and appointments. The application is developed following a layered architecture pattern, with PostgreSQL as the database and Spring Data JPA for data access. The API endpoints are documented for easy integration and usage.

## License

This project has no license. Feel free to use, modify, and distribute the code.
