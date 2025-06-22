package com.example.moviebooking.dto.response;

import com.example.moviebooking.enums.BookingStatus;

public record BookingResponse(
        String bookingId,
        BookingStatus status,
        String message

) {
}
