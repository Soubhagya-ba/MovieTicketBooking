package com.example.moviebooking.dto.request;

import com.example.moviebooking.enums.UserRole;

import java.time.LocalDate;

public record UserDetailsRequest(
        String name,
        String email,
        String password,
        long phoneNo,
        UserRole userRole,
        LocalDate dateOfBirth
) {
}
