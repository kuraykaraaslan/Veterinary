/*
 * This interface is used to perform CRUD operations on the appointment table in the database
 * It extends the JpaRepository interface
 * The AppointmentRepository interface is a parameterized interface as it extends the JpaRepository interface
 * 
 * Attributes:
 * id: Long - the id of the appointment
 * date: LocalDate - the date of the appointment
 * time: LocalTime - the time of the appointment
 * vetenerian: Vetenerian - the vetenerian that will take care of the animal
 * animal: Animal - the animal that will be taken care of
 * customer: Customer - the customer that owns the animal
 * description: String - the description of the appointment
 * status: String - the status of the appointment
 * price: Double - the price of the appointment
 */


package com.veterinary.management.repositories;

import com.veterinary.management.entities.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.veterinary.management.entities.Animal;
import com.veterinary.management.entities.Veterinarian;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findAppointmentById(Long id);
    Appointment findAppointmentByDate(LocalDate date);
    Appointment findAppointmentByTime(LocalTime time);
    Appointment findAppointmentByVeterinarian(Veterinarian veterinarian);
    Appointment findAppointmentByAnimal(Animal animal);
    Appointment findAppointmentByDescription(String description);
    Appointment findAppointmentByStatus(String status);
    Appointment findAppointmentByPrice(Double price);
    List<Appointment> findAppointmentsByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Appointment> findAppointmentsByVeterinarianId(Long id);
    
}

