package com.vijay.learning.useroperations.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EMPLOYEE_DETAILS")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private Double salary;
    private String designation;
}