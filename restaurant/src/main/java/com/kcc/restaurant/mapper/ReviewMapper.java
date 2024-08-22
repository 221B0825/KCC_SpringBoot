package com.kcc.restaurant.mapper;

import com.kcc.restaurant.bean.Review;
import com.kcc.restaurant.dto.ReviewResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    public Review findReviewById(Integer id);
    public void createReview(Review review);
    public void deleteReview(Integer id);
}
