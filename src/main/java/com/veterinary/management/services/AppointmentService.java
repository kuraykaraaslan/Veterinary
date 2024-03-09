package com.veterinary.management.services;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.veterinary.management.models.Animal;
import com.veterinary.management.models.Appointment;
import com.veterinary.management.models.Doctor;
import com.veterinary.management.requests.AppointmentRequest;
import com.veterinary.management.repositories.AppointmentRepository;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;
    private final AnimalService animalService;

    /*
     * This method handles the request for getting all appointments.
     * 
     * @return the list of all appointments.
     */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    /*
     * This method handles the request for getting an appointment by id.
     * 
     * @param id the id of the appointment.
     * 
     * @return the appointment with the given id.
     * 
     * @throws UnsupportedOperationException if the id is null
     */
    public Appointment getAppointmentById(Long id) {
        if (id == null) {
            throw new UnsupportedOperationException("Id cannot be null");
        }
        return appointmentRepository.findById(id).orElse(null);
    }

    /*
     * This method handles the request for adding a new appointment.
     * 
     * @param appointmentRequest the request for adding a new appointment.
     * 
     * @return the added appointment.
     * 
     * @throws UnsupportedOperationException if there is no doctor with the given id
     * 
     * @throws UnsupportedOperationException if there is no animal with the given id
     */
    public Appointment addAppointment(AppointmentRequest appointmentRequest) {
        Appointment appointment = new Appointment();
        Doctor doctor = doctorService.getDoctorById(appointmentRequest.getDoctorId());
        if (doctor == null) {
            throw new UnsupportedOperationException("There is no doctor with the given id");
        }
        appointment.setDoctor(doctor);
        Animal animal = animalService.getAnimalById(appointmentRequest.getAnimalId());
        if (animal == null) {
            throw new UnsupportedOperationException("There is no animal with the given id");
        }

        // TODO: check if the animal is already in an appointment

        appointment.setAnimal(animal);
        appointment.setDate(appointmentRequest.getDate());
        appointmentRepository.save(appointment);

        return appointmentRepository.save(appointment);
    }

    /*
     * This method handles the request for updating an appointment.
     * 
     * @param id the id of the appointment.
     * 
     * @param appointmentRequest the request for updating an appointment.
     * 
     * @return the updated appointment.
     * 
     * @throws UnsupportedOperationException if there is no doctor with the given id
     * 
     * @throws UnsupportedOperationException if there is no animal with the given id
     */
    public Appointment updateAppointment(Long id, AppointmentRequest appointmentRequest) {
        if (id == null) {
            throw new UnsupportedOperationException("Id cannot be null");
        }
        Appointment appointment = appointmentRepository.findById(id).orElse(null);

        if (appointment == null) {
            throw new UnsupportedOperationException("There is no appointment with the given id");
        }
        Doctor doctor = doctorService.getDoctorById(appointmentRequest.getDoctorId());
        if (doctor == null) {
            throw new UnsupportedOperationException("There is no doctor with the given id");
        }

        //Check if the doctor is available
        if (!doctor.isDoctorHasAvailableDateOnDate(appointmentRequest.getDate())) {
            throw new UnsupportedOperationException("Doctor is not available on the given date");
        }

        //Check if the doctor is has an appointment on the given date
        if (doctor.isDoctorHasAppointmentOnDate(appointmentRequest.getDate())) {
            throw new UnsupportedOperationException("Doctor has an appointment on the given date");
        }


        appointment.setDoctor(doctor);
        Animal animal = animalService.getAnimalById(appointmentRequest.getAnimalId());
        if (animal == null) {
            throw new UnsupportedOperationException("There is no animal with the given id");
        }
        appointment.setAnimal(animal);
        appointment.setDate(appointmentRequest.getDate());
        return appointmentRepository.save(appointment);

    }

    /*
     * This method handles the request for deleting an appointment.
     * 
     * @param id the id of the appointment.
     * 
     * @throws UnsupportedOperationException if the id is null
     */
    public void deleteById(Long id) {
        if (id == null) {
            throw new UnsupportedOperationException("Id cannot be null");
        }
        appointmentRepository.deleteById(id);
    }

    /*
     * This method handles the request for getting all the appointments for a specific animal between two dates.
     * 
     * @param animalId the id of the animal.
     * 
     * @param startDate the start date.
     * 
     * @param endDate the end date.
     * 
     * @return the list of all the appointments for the specific animal between the two dates.
     */
    public List<Appointment> findByAnimalIdAndDateBetween(Long animalId, LocalDateTime startDate, LocalDateTime endDate) {
        return appointmentRepository.findByAnimalIdAndDateBetween(animalId, startDate, endDate);
    }

    /*
     * This method handles the request for getting all the appointments for a specific doctor between two dates.
     * 
     * @param doctorId the id of the doctor.
     * 
     * @param startDate the start date.
     * 
     * @param endDate the end date.
     * 
     * @return the list of all the appointments for the specific doctor between the two dates.
     */
    public List<Appointment> findByDoctorIdAndDateBetween(Long doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        return appointmentRepository.findByDoctorIdAndDateBetween(doctorId, startDate, endDate);
    }
}
