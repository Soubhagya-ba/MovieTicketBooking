package com.example.moviebooking.repository;

import com.example.moviebooking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat , String> {
}
