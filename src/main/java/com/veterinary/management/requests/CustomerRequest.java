package com.veterinary.management.requests;

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
public class CustomerRequest {

    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;

}
