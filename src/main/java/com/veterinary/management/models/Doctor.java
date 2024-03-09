package com.veterinary.management.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.Data;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<AvailableDate> availableDates;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Appointment> appointments;


    /*
     * This method returns true if doctor has available date on date
     */
    public boolean isDoctorHasAvailableDateOnDate(LocalDate date) {
        for (AvailableDate availableDate : availableDates) {
            if (availableDate.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    /*
     * This method returns true if doctor has available date on date
     */
    public boolean isDoctorHasAvailableDateOnDate(LocalDateTime localDateTime) {
        LocalDate date = localDateTime.toLocalDate();
        for (AvailableDate availableDate : availableDates) {
            if (availableDate.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    /*
     * This method returns true if doctor has appointment on localDateTime
     */
    public boolean isDoctorHasAppointmentOnDate(LocalDateTime localDateTime) {
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(localDateTime)) {
                return true;
            }
        }
        return false;
    }

}
