package com.example.moviebooking.controller.exceptionhandle;

import com.example.moviebooking.dto.wrapper.ErrorResponse;
import com.example.moviebooking.exception.InvalidTheaterIdException;
import com.example.moviebooking.exception.ScreenNotFoundException;
import com.example.moviebooking.exception.TheaterOwnerNotLoginException;
import com.example.moviebooking.exception.UnAuthorizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TheaterExceptionHandler {

    @ExceptionHandler(TheaterOwnerNotLoginException.class)
    public ResponseEntity<ErrorResponse> handleTheaterOwnerNotLogIn(TheaterOwnerNotLoginException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    @ExceptionHandler(UnAuthorizeException.class)
    public ResponseEntity<ErrorResponse> handleUnAuthorize(UnAuthorizeException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
    @ExceptionHandler(ScreenNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleScreenNotFound(ScreenNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(InvalidTheaterIdException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTheaterId(InvalidTheaterIdException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
