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
public class AnimalRequest {
    
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate birthDate;
    private Long customerId;

}

