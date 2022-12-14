package com.demo.onlineLibraryAnaMariaDoroftei.security;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {

    public String createAuthToken(String email, String password){
        String unEncodedToken = email + ":" + password;
        String encodedToken = Base64.getEncoder()
                .encodeToString(unEncodedToken.getBytes());

        return "Basic " + encodedToken;
    }
}
