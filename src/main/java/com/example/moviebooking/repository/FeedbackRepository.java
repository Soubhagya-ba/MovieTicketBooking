package com.example.moviebooking.repository;

import com.example.moviebooking.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, String> {

}
