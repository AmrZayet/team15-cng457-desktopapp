package com.example.demo.Service;

import com.example.demo.Repository.ReviewRepository;
import com.example.demo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    /*Get All Reviews*/
    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    /*Get a Review by an ID*/
    public Review getReview(int id) {
        return reviewRepository.findById(id).orElse(null);
    }


    public String deleteReview(int reviewID) {
        reviewRepository.deleteById(reviewID);
        return String.format("Review %d is deleted", reviewID);
    }}
