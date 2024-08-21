package com.kcc.restaurant.dto;

import java.sql.Timestamp;

public class RestaurantListDTO {

    private Integer restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private Timestamp created_at;
    private Timestamp updated_at;

    public RestaurantListDTO(Integer restaurantId, String restaurantName, String restaurantAddress, Timestamp created_at, Timestamp updated_at) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }
}

