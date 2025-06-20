package com.example.moviebooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "verify_user")
public class VerifyUser {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "email", updatable = false, nullable = false)
    private String email;

    @Column(name = "otp", nullable = false, updatable = false)
    private int otp;

    @Column(name = "expire_at", nullable = false, updatable = false)
    private LocalDateTime expireAt;
}
