package com.kcc.restful.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

//@Entity
//@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"password", "ssn"})
public class User {

//    @Id
//    @GeneratedValue
    private Integer id;
    
    @Schema(title = "사용자 이름", description = "사용자 이름을 입력")
    @Size(min = 2, max = 20, message = "이름은 2글자 이상 입력해 주십시오.")
    private String name;
    @Schema(title = "사용자 등록일", description = "사용자 등록일 입력")
    @Past(message = "등록일은 미래 날짜를 입력할 수 없습니다.")
    private Date joinDate;

//    @JsonIgnore
    @Pattern(regexp = "[0-9a-zA-Z]*", message = "특수문자 금지")
    private String password;
//    @JsonIgnore
    private String ssn;

//    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(Integer id, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}
