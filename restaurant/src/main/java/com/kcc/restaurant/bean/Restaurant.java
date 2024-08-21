package com.kcc.restaurant.bean;

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
public class Restaurant {
    private Integer id;
    private String name;
    @Pattern(regexp = "[0-9a-zA-Z]*", message = "특수문자 금지")
    private String address;
    private Date created_at;
    private Date updated_at;

    private List<Review> reviews;
    private List<Menu> menus;

}
