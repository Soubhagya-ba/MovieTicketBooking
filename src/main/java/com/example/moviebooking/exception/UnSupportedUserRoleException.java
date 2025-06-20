package com.example.moviebooking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UnSupportedUserRoleException extends RuntimeException{
    private final String message;
}
