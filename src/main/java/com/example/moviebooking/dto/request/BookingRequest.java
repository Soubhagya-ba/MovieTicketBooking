package com.example.moviebooking.dto.request;

import com.example.moviebooking.entity.Seat;

import java.util.List;

public record BookingRequest(
        String showId,
        List<String> seatIds,
        double totalAmount
) {
}
