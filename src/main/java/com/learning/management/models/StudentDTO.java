package com.learning.management.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private String ssnNumber;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String address;
    @Email
    private String email;
    private String phone;
}
