package com.kcc.security.config;

import com.kcc.security.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    private static final String [] WHITE_LIST = {
            "/",
            "/common/**",
            "/WEB-INF/views/**",
            "/joinForm",
            "/join",
            "/loginForm",
            "/h2-console/**"

    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize -> authorize.requestMatchers(WHITE_LIST).permitAll()
//                .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
//                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .anyRequest().authenticated()).csrf(
                csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console())
        ).headers(
                headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
        ).formLogin(form -> form.loginPage("/loginForm").loginProcessingUrl("/login")
                .defaultSuccessUrl("/main")).oauth2Login(oauth2Login -> oauth2Login.loginPage("/loginForm").userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(principalOauth2UserService)));
        return http.build();

    }

    // 비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
