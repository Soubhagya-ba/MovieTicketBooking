package com.example.moviebooking.dto.response;

public record TheaterResponse(
        String theaterId,
        String name,
        String address,
        String city,
        String landmark,
        String createdBy
) {
}
