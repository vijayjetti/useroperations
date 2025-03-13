package com.vijay.learning.useroperations.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER_DETAILS")
public class User {
    @Id
    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private String department;
    private String email;
    private Integer yearOfJoining;

}
