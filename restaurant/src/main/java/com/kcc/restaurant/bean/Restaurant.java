package com.kcc.restaurant.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("restaurantFilter")
public class Restaurant {
    private Integer id;
    private String name;
    @Pattern(regexp = "[0-9a-zA-Z]*", message = "특수문자 금지")
    private String address;
    private Timestamp created_at;
    private Timestamp updated_at;

    private List<Review> reviews;
    private List<Menu> menus;

    public Restaurant(Integer id, String name, String address, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
