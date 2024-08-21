package com.kcc.restaurant.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    private Integer id;
    private String name;
    private Integer price;
    private Timestamp created_at;
    private Timestamp updated_at;

    private Integer restaurant_id;
}
