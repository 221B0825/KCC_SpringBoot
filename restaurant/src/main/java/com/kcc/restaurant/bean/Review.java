package com.kcc.restaurant.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    private Integer id;
    private String content;
    private Double score;
    private Timestamp created_at;
    private Integer restaurant_id;

}
