package com.kcc.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcc.jwt.config.PrincipalDetail;
import com.kcc.jwt.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;


    // if trying to "login", then call this method
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("called attemptAuthentication");
        // 1. get username & password
        try {
            ObjectMapper om = new ObjectMapper(); // parsing json data
            // using Object Mapper
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println("ObjectMapper :: readValue"+user);

            // create AuthToken
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            // 2. call "loadUserByUserName()" in PrincipalDetailService for authenticate
            Authentication auth = authenticationManager.authenticate(authenticationToken);

            // 3. get principalDetail
            PrincipalDetail principalDetail = (PrincipalDetail) auth.getPrincipal();
            System.out.println("PrincipalDetail :: "+principalDetail);
            return auth;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
