package com.kcc.restaurant.controller;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.kcc.restaurant.bean.Restaurant;
import com.kcc.restaurant.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
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
    public MappingJacksonValue restaurants() {
        List<Restaurant> list = restaurantService.findAllRestaurants();

        // select variable
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "address", "created_at", "updated_at");
        // add Filter
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("restaurantFilter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @GetMapping("/restaurant/{id}")
    public MappingJacksonValue restaurantById(@PathVariable int id) {
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        // select variable
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "address", "created_at", "updated_at", "menus");
        // add Filter
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("restaurantFilter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(restaurant);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @PostMapping("/restaurant")
    public ResponseEntity<Restaurant> addRestaurant(@Valid @RequestBody Restaurant restaurant) {
        Restaurant newRestaurant = restaurantService.createRestaurant(restaurant);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newRestaurant.getId()).toUri();
        return ResponseEntity.created(location).body(newRestaurant);
    }

    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteRestaurant(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).body("맛집 삭제 완료");
    }

    @PutMapping("/restaurant/{id}")
    public ResponseEntity<String> updateRestaurant(@PathVariable int id, @RequestBody Restaurant restaurant) {
        restaurantService.updateRestaurant(restaurant);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }
}
