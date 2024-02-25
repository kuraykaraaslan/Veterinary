![veterinary](/static/logo.png)

# Veterinary Management System

This is a Spring Boot application for managing a veterinary clinic's operations. The system provides APIs for managing veterinarians, customers, animals, vaccinations, and appointments.

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

- **Doctors**:
  - `GET /api/doctors`: Get all veterinarians.
  - `POST /api/doctors`: Create a new veterinarian.
  - `GET /api/doctors/{id}`: Get details of a veterinarian by ID.
  - `PUT /api/doctors/{id}`: Update details of a veterinarian.
  - `DELETE /api/doctors/{id}`: Delete a veterinarian by ID.
  - `GET /api/doctors/{id}/appointments`: Get all appointments of a veterinarian by ID.
  - `GET /api/doctors/{id}/working-days`: Get all working days of a veterinarian by ID.
  - `POST /api/doctors/{id}/working-days`: Add a working day for a veterinarian by ID.
  - `DELETE /api/doctors/{id}/working-days/{day}`: Delete a working day for a veterinarian by ID.

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


## Sample Data

The application provides sample data for veterinarians, customers, animals, vaccinations, and appointments. The sample data is loaded into the database on application startup using the `data.sql` file.

## Conclusion

The Veterinary Management System is a Spring Boot application for managing a veterinary clinic's operations. The system provides APIs for managing veterinarians, customers, animals, vaccinations, and appointments. The application is developed following a layered architecture pattern, with PostgreSQL as the database and Spring Data JPA for data access. The API endpoints are documented for easy integration and usage.

## License

This project has no license. Feel free to use, modify, and distribute the code.
