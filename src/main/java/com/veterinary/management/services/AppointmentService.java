/*
 * This class is used to provide the service for the Appointment entity
 * It is annotated with the @Service annotation
 * The AppointmentService class is a parameterized class as it is annotated with the @Service annotation
 * The AppointmentService class is used to provide the service for the Appointment entity
 * 
 * Methods:
 * getAppointments: List<Appointment> - this method is used to get all the appointments
 * addNewAppointment: void - this method is used to add a new appointment
 * deleteAppointment: void - this method is used to delete an appointment
 * updateAppointment: void - this method is used to update an appointment
 * getAppointmentById: Appointment - this method is used to get an appointment by its id
 * getAppointmentByDate: Appointment - this method is used to get an appointment by its date
 * getAppointmentByTime: Appointment - this method is used to get an appointment by its time
 * getAppointmentByAnimal: Appointment - this method is used to get an appointment by its animal
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

package com.veterinary.management.services;

import com.veterinary.management.entities.Appointment;
import com.veterinary.management.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }

    public void addNewAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    public void updateAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public Appointment getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).orElse(null);
    }
}