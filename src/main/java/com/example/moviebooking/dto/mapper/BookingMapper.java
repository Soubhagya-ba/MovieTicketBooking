package com.example.moviebooking.dto.mapper;

import com.example.moviebooking.dto.request.BookingRequest;
import com.example.moviebooking.dto.response.BookingResponse;
import com.example.moviebooking.entity.Booking;
import com.example.moviebooking.enums.BookingStatus;

import java.time.LocalDateTime;

public class BookingMapper {

    public static Booking toEntity(BookingRequest source, Booking target, String userId){
        target.setBookingStatus(BookingStatus.PENDING);
        target.setTotalAmount(source.totalAmount());
        target.setCreatedAt(LocalDateTime.now());
        target.setUpdateAt(LocalDateTime.now());
        target.setCreatedBy(userId);
        return target;
    }

    public static BookingResponse toResponse(Booking booking){
        return new BookingResponse(
                booking.getBookingId(),
                BookingStatus.PENDING,
                "Seats locked. Proceed to payment within 5 minutes."
        );
    }
}
