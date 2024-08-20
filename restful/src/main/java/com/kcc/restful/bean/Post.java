package com.kcc.restful.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Integer user_id;
    // 현재 post 기준으로 post가 many
    // lazy -> post search 할 때 모든 User 객체를 가져오지 않음
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnore
//    private User user;
}
