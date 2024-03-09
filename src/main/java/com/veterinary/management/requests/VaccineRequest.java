package com.veterinary.management.requests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class VaccineRequest {

    private String name;
    private LocalDate applicationDate;
    private LocalDate expirationDate;
    private Long animalId;
    
}

