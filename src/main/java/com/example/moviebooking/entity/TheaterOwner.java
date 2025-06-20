package com.example.moviebooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class TheaterOwner extends UserDetails{

    @OneToMany(mappedBy = "theaterOwner",fetch = FetchType.LAZY)
    private List<Theater> theaters;
}
