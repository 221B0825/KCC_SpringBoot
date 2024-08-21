package com.kcc.restaurant.service;

import com.kcc.restaurant.bean.Restaurant;
import com.kcc.restaurant.mapper.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantMapper restaurantMapper;

    public List<Restaurant> findAllRestaurants(){
        return restaurantMapper.findAllRestaurants();
    }

    public Restaurant findRestaurantById(int id){
        return restaurantMapper.findRestaurantById(id);
    }
}
