package com.example.moviebooking.serviceimpl;

import com.example.moviebooking.dto.mapper.MovieMapper;
import com.example.moviebooking.dto.request.MovieRequest;
import com.example.moviebooking.dto.response.MovieResponse;
import com.example.moviebooking.entity.Movie;
import com.example.moviebooking.repository.MovieRepository;
import com.example.moviebooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public MovieResponse addMovie(MovieRequest request) {
        Movie movie = MovieMapper.toEntity(request,new Movie());
        movieRepository.save(movie);
        return MovieMapper.toResponse(movie);
    }

    @Override
    public Map<String, String> getMovieIdName() {
        List<Movie> movies = movieRepository.findAll();
        Map<String , String> movieMap = new HashMap<>();
        for (Movie m : movies){
            movieMap.put(m.getMovieId(),m.getTitle());
        }
        return movieMap;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Transactional
    @Override
    public List<MovieResponse> addMultipleMovie(List<MovieRequest> request) {
        List<Movie> movies = MovieMapper.toEntity(request);
        System.out.println(movies);
        movies = movieRepository.saveAll(movies);
        return MovieMapper.toResponse(movies);
    }
}
