package com.example.moviebooking.controller.exceptionhandle;

import com.example.moviebooking.dto.wrapper.ErrorResponse;
import com.example.moviebooking.exception.IllegalSeatException;
import com.example.moviebooking.exception.InvalidBookingIdException;
import com.example.moviebooking.exception.MovieNotFoundException;
import com.example.moviebooking.exception.MovieShowNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MovieExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMovieNotFound(MovieNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }@ExceptionHandler(MovieShowNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleShowNotFound(MovieShowNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }@ExceptionHandler(InvalidBookingIdException.class)
    public ResponseEntity<ErrorResponse> handleInvalidBookingIdException(InvalidBookingIdException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }@ExceptionHandler(IllegalSeatException.class)
    public ResponseEntity<ErrorResponse> handleIllegalSeat(IllegalSeatException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
