package com.example.moviebooking.entity;

import com.example.moviebooking.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user_details")
public class UserDetails {

    @Id
    @Column(name = "user_id",updatable = false,nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "phone_no",updatable = false)
    private long phoneNo;

    @Column(name = "user_role",nullable = false,updatable = false)
    private UserRole userRole;

    @Column(name = "date_of_birth",nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updateAt;

}
