package com.kcc.jwt.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.kcc.jwt.config.PrincipalDetail;
import com.kcc.jwt.model.User;
import com.kcc.jwt.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        super.doFilterInternal(request, response, chain);
        System.out.println("call JwtAuthorizationFilter");

        String jwtHeader = request.getHeader("Authorization");
        System.out.println("jwtToken :: " + jwtHeader);

        // check jwtHeader
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");
        String username = JWT.require(Algorithm.HMAC512("kcc"))
                .build().verify(jwtToken).getClaim("username").asString();
        System.out.println("username: " + username);

        if(username != null){
            // get user
            User userEntity = userRepository.findByUsername(username);
            // convert principalDetail
            PrincipalDetail principalDetail = new PrincipalDetail(userEntity);

            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetail, null, principalDetail.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }
    }
}
