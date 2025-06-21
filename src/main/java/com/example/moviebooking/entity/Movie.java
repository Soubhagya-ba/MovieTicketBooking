package com.example.moviebooking.entity;

import com.example.moviebooking.enums.Certificate;
import com.example.moviebooking.enums.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Entity
@Getter
@Setter
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "movie_id",nullable = false,updatable = false)
    private String movieId;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "cast" , nullable = false)
    private String[] cast;

    @Column(name = "runtime",nullable = false)
    private Duration runtime;

    @Column(name = "certificate",nullable = false)
    private Certificate certificate;

    @Column(name = "genre",nullable = false)
    private Genre genre;
}
