package com.kcc.restaurant.service;

import com.kcc.restaurant.bean.Menu;
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

    public Restaurant createRestaurant(Restaurant restaurant){
        restaurantMapper.createRestaurant(restaurant);
        return restaurant;
    }

    public void deleteRestaurant(int id){
        restaurantMapper.deleteRestaurant(id);
    }

    public Restaurant updateRestaurant(Restaurant restaurant){
        restaurantMapper.updateRestaurant(restaurant);
        return restaurant;
    }

}
