package com.kcc.restaurant.service;

import com.kcc.restaurant.bean.Menu;
import com.kcc.restaurant.bean.Restaurant;
import com.kcc.restaurant.dto.RestaurantListDTO;
import com.kcc.restaurant.exception.RestaurantNotFoundException;
import com.kcc.restaurant.mapper.MenuMapper;
import com.kcc.restaurant.mapper.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private MenuMapper menuMapper;

    public List<RestaurantListDTO> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantMapper.findAllRestaurants();
        return restaurants.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private RestaurantListDTO convertToDTO(Restaurant restaurant) {
        RestaurantListDTO dto = new RestaurantListDTO(restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getCreated_at(), restaurant.getUpdated_at());
        return dto;
    }

    public Restaurant findRestaurantById(int id){
        if(restaurantMapper.findRestaurantById(id) == null){
            throw new RestaurantNotFoundException(String.format("Restaurant with id %s not found", id));
        }
        return restaurantMapper.findRestaurantById(id);
    }

    public Restaurant createRestaurant(Restaurant restaurant){
        restaurantMapper.createRestaurant(restaurant);

        if(restaurant.getMenus() != null){
            for(Menu menu : restaurant.getMenus()){
                menu.setRestaurant_id(restaurant.getId());
                System.out.println(menu);
                menuMapper.createMenu(menu);
            }
        }

        return findRestaurantById(restaurant.getId());
    }

    public void deleteRestaurant(int id){
        if(restaurantMapper.findRestaurantById(id) == null){
            throw new RestaurantNotFoundException(String.format("Restaurant with id %s not found", id));
        }
        restaurantMapper.deleteRestaurant(id);
    }

    public Restaurant updateRestaurant(int id, Restaurant restaurant){
        if(restaurantMapper.findRestaurantById(id) == null){
            throw new RestaurantNotFoundException(String.format("Restaurant with id %s not found", id));
        }

        restaurant.setId(id);
        restaurantMapper.updateRestaurant(restaurant);

        return findRestaurantById(id);
    }

}
