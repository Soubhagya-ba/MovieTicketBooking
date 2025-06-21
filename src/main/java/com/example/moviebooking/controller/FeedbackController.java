package com.example.moviebooking.controller;

import com.example.moviebooking.dto.request.FeedbackRequest;
import com.example.moviebooking.serviceimpl.FeedbackImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackController {

    @Autowired
    private FeedbackImpl feedback;

    public ResponseEntity<String> giveFeedback(@RequestBody FeedbackRequest request){
       String message = feedback.giveFeedback(request);
    }
}
