package com.example.moviebooking.repository;

import com.example.moviebooking.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater , String> {
}
