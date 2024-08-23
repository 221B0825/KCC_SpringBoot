package com.kcc.security.oauth;

import com.kcc.security.model.User;
import com.kcc.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        /*
        System.out.println("userRequest = " + userRequest);
        System.out.println("getClientRegistation" + userRequest.getClientRegistration());
        System.out.println("getAccessToken" + userRequest.getAccessToken());
        System.out.println("getAttributes: "+super.loadUser(userRequest).getAttributes());
        */
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getAttributes: "+oAuth2User.getAttributes());
        // click "google login" -> show google login page -> login success -> request Access Token
        // userRequest info -> call "loadUser" function -> get userProfile from google

        String provider = userRequest.getClientRegistration().getRegistrationId(); // google
        String providerId = oAuth2User.getAttribute("sub"); // unique id
        String username = provider+"_"+providerId; // google_uniqueId
        String email = oAuth2User.getAttribute("email");
        String role = "ROLE_USER";

        User userEntity = userRepository.findByUsername(username);

        // not found user -> sign in process
        if(userEntity == null) {
            // sign in
            userEntity = User.builder().username(username).password("1234").email(email).role(role).provider(provider).providerId(providerId).build();
            userRepository.save(userEntity);
        }

        return super.loadUser(userRequest);
    }
}
