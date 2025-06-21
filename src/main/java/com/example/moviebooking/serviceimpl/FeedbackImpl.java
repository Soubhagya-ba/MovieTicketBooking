package com.example.moviebooking.serviceimpl;

import com.example.moviebooking.dto.request.FeedbackRequest;
import com.example.moviebooking.entity.Feedback;
import com.example.moviebooking.entity.Movie;
import com.example.moviebooking.entity.Theater;
import com.example.moviebooking.entity.UserDetails;
import com.example.moviebooking.exception.InvalidTheaterIdException;
import com.example.moviebooking.exception.MovieNotFoundException;
import com.example.moviebooking.exception.UserNotLoggedInException;
import com.example.moviebooking.repository.FeedbackRepository;
import com.example.moviebooking.repository.MovieRepository;
import com.example.moviebooking.repository.TheaterRepository;
import com.example.moviebooking.repository.UserDetailsRepository;
import com.example.moviebooking.security.AuthUtilities;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FeedbackImpl {

    private MovieRepository movieRepository;
    private TheaterRepository theaterRepository;
    private UserDetailsRepository userDetailsRepository;
    private FeedbackRepository feedbackRepository;

    public String giveFeedback(FeedbackRequest request) {
        Movie movie = movieRepository.findById(request.movieId()).orElseThrow(()->new MovieNotFoundException("Movie Not Found!!"));
        Theater theater = theaterRepository.findById(request.theaterId()).orElseThrow(()->new InvalidTheaterIdException("Theater Not Found!!!"));
        String userId = AuthUtilities.getUserName().orElseThrow(()->new UserNotLoggedInException("User Not Logged In!!"));
        UserDetails userDetails = userDetailsRepository.findById(userId).orElseThrow();

        Feedback feedback = new Feedback();
        feedback.setReview(request.review());
        feedback.setRatings(request.rating());
        feedback.setMovie(movie);
        feedback.setTheater(theater);
        feedback.setUserDetails(userDetails);
        feedbackRepository.save(feedback);
        return "Thank You For Your Feedback.. It's Really Matter To Us";
    }
}
