package com.example.moviebooking.controller;

import com.example.moviebooking.dto.request.BookingRequest;
import com.example.moviebooking.dto.response.BookingResponse;
import com.example.moviebooking.entity.ShowSeat;
import com.example.moviebooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @PostMapping("/booking")
    public ResponseEntity<?> pendingBooking(@RequestBody BookingRequest request){
      BookingResponse response =  bookingService.pendingBooking(request);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/confirmBooking")
    public ResponseEntity<String> confirmBooking(@RequestParam String bookingId){
      String message =  bookingService.confirmBooking(bookingId);
      return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @PostMapping("/cancelBooking")
    public ResponseEntity<String> cancelBooking(@RequestParam String bookingId){
      String message =  bookingService.cancelBooking(bookingId);
      return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @GetMapping("/availableSeat")
    public ResponseEntity<List<String>> getAvailableSeat(@RequestParam String showId){
        List<String> seatList = bookingService.getAvailableSeat(showId);
        return ResponseEntity.status(HttpStatus.CREATED).body(seatList);
    }
}
