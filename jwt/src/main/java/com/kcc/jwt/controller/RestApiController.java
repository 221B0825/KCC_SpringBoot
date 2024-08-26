package com.kcc.jwt.controller;

import com.kcc.jwt.model.User;
import com.kcc.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestApiController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @GetMapping("/home")
    public String home() {
        return "<h1>HOME</h1>";
    }

    @PostMapping("/join")
    public String join(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        // save user in database
        userRepository.save(user);
        return "<h1>JOIN SUCCESS</h1>";
    }

    @GetMapping("/user")
    public String user() {
        return "<h1>USER</h1>";
    }

    @GetMapping("/manager/mm")
    public String manager() {
        return "<h1>manager</h1>";
    }


    @GetMapping("/admin/admin")
    public String admin() {
        return "<h1>admin</h1>";
    }
}
