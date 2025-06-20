package com.example.moviebooking.dto.response;

import com.example.moviebooking.enums.UserRole;

import java.time.LocalDate;

public record UserDetailsResponse(
        String userId,
        String name,
        String email,
        long phoneNo,
        UserRole userRole,
        LocalDate dateOfBirth
) {
}
