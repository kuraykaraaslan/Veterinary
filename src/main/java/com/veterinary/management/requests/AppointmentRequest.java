package com.veterinary.management.requests;

import java.time.LocalDateTime;
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
public class AppointmentRequest {
    
    private LocalDateTime date;
    private Long doctorId;
    private Long animalId;

}