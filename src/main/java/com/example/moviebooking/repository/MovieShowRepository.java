package com.example.moviebooking.repository;

import com.example.moviebooking.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieShowRepository extends JpaRepository<Show , String> {
}
