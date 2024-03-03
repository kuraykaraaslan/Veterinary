package com.veterinary.management.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.veterinary.management.entity.Doctor;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateRequestDto {

    private LocalDate availableDate;
    private Doctor doctor;
}
