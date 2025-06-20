package com.example.moviebooking.repository;

import com.example.moviebooking.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {

    Optional<UserDetails> findByEmail(String email);
}
