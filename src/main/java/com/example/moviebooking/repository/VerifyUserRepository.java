package com.example.moviebooking.repository;

import com.example.moviebooking.entity.VerifyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerifyUserRepository extends JpaRepository<VerifyUser , String> {
    Optional<VerifyUser> findByEmail(String email);

    void deleteByEmail(String email);
}
