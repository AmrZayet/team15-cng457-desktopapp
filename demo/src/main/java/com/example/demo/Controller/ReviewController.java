package com.example.demo.Controller;

import com.example.demo.Service.ReviewService;
import com.example.demo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("getReviews")
    public List<Review> getReviews() {
        return reviewService.getReviews();
    }
}
