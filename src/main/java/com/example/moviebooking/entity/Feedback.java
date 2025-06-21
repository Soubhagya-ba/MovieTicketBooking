package com.example.moviebooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "feedback")
public class Feedback {

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id")
    private UserDetails userDetails;

    @ManyToOne(optional = true)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(optional = true)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "feedback_id",nullable = false,updatable = false)
    private String feedbackId;

    @Column(name = "ratings", nullable = false)
    private int ratings;

    @Column(name = "review",nullable = false)
    private String review;

    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

}
