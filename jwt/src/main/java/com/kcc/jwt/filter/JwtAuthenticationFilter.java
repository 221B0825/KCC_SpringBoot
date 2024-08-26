package com.kcc.jwt.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcc.jwt.config.PrincipalDetail;
import com.kcc.jwt.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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
import java.util.Date;

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

    // If authentication is successful, run automatically this method
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication :: login success");

        // create JWT Token & send
        PrincipalDetail principalDetail = (PrincipalDetail) authResult.getPrincipal();
        // (60000 * 10) = 10 minutes
        String jwtToken = JWT.create().withSubject("jwtToken").withExpiresAt(new Date(System.currentTimeMillis() + (60000*10)))
                .withClaim("id", principalDetail.getUser().getId())
                .withClaim("username", principalDetail.getUsername())
                .sign(Algorithm.HMAC512("kcc"));

        response.addHeader("Authorization", "Bearer " + jwtToken);
    }
}
