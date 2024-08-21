package com.kcc.restaurant.controller;


import com.kcc.restaurant.bean.Restaurant;
import com.kcc.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> restaurants() {
        return restaurantService.findAllRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant restaurantById(@PathVariable int id) {
        return restaurantService.findRestaurantById(id);
    }
}
