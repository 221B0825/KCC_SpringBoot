package com.kcc.restaurant.mapper;

import com.kcc.restaurant.bean.Menu;
import com.kcc.restaurant.bean.Restaurant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    public List<Restaurant> findAllRestaurants();
    public Restaurant findRestaurantById(int id);
    public void createRestaurant(Restaurant restaurant);
    public void deleteRestaurant(int id);
    public void updateRestaurant(Restaurant restaurant);
}
