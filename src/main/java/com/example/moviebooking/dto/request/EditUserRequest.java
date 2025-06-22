package com.example.moviebooking.dto.request;

import java.time.LocalDate;

public record EditUserRequest(
        String name,
        long phoneNo,
        LocalDate dateOfBirth
) {
}
