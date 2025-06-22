package com.example.moviebooking.service;

import com.example.moviebooking.dto.request.BookingRequest;
import com.example.moviebooking.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse pendingBooking(BookingRequest request);

    String confirmBooking(String bookingId);

    String cancelBooking(String bookingId);

    List<String> getAvailableSeat(String showId);
}
