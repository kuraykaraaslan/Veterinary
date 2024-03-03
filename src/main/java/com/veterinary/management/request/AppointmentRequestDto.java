package com.veterinary.management.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.veterinary.management.entity.Animal;
import com.veterinary.management.entity.Doctor;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDto {

    private LocalDateTime date;
    private Doctor doctor;
    private Animal animal;
}
