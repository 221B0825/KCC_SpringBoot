package com.kcc.restaurant.mapper;

import com.kcc.restaurant.bean.Review;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    public Review findReviewById(Integer id);
    public void createReview(Review review);

}
