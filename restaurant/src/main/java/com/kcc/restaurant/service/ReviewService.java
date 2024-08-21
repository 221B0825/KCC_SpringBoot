package com.kcc.restaurant.service;

import com.kcc.restaurant.bean.Restaurant;
import com.kcc.restaurant.bean.Review;
import com.kcc.restaurant.exception.RestaurantNotFoundException;
import com.kcc.restaurant.mapper.RestaurantMapper;
import com.kcc.restaurant.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private RestaurantMapper restaurantMapper;


    public Review findReviewById(int id){
        if(reviewMapper.findReviewById(id) == null){
            // throw exception
        }
        return reviewMapper.findReviewById(id);
    }

    public Review createReview(Review review) {
        if(restaurantMapper.findRestaurantById(review.getRestaurant_id()) == null) {
            throw new RestaurantNotFoundException(String.format("Restaurant %s not found", review.getRestaurant_id()));
        }
        reviewMapper.createReview(review);
        return findReviewById(review.getId());
    }

    public void deleteReviewById(int id) {
        if(reviewMapper.findReviewById(id) == null){
            // throw exception
        }
        reviewMapper.deleteReview(id);
    }
}
