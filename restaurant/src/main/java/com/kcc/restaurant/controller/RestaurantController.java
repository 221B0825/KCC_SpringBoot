package com.kcc.restaurant.controller;


import com.kcc.restaurant.bean.Restaurant;
import com.kcc.restaurant.dto.RestaurantListDTO;
import com.kcc.restaurant.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
//@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantListDTO>> restaurants() {
        List<RestaurantListDTO> restaurantList = restaurantService.findAllRestaurants();
        return ResponseEntity.ok(restaurantList);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> restaurantById(@PathVariable int id) {
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return ResponseEntity.ok(restaurant);
    }

    @PostMapping("/restaurant")
    public ResponseEntity<Restaurant> addRestaurant(@Valid @RequestBody Restaurant restaurant) {
        Restaurant newRestaurant = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.ok(newRestaurant);
    }

    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok("맛집 삭제 완료");
    }

    @PutMapping("/restaurant/{id}")
    public ResponseEntity<String> updateRestaurant(@PathVariable int id, @RequestBody Restaurant restaurant) {
        restaurantService.updateRestaurant(restaurant);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }
}
