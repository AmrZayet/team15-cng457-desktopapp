package com.example.demo.Controller;

import com.example.demo.Service.ReviewService;
import com.example.demo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/***
 * @author of this class is Mert Özçelik and Amr Zayet
 * This is Review Controller class for the web service.
 * @version PV
 */


@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    /**
     * This method is allow us to get Reviews
     * @return the result coming from Review Service
     */
    @GetMapping("getReviews")
    public List<Review> getReviews() {
        return reviewService.getReviews();
    }
}
