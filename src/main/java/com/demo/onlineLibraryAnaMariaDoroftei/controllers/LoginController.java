package com.demo.onlineLibraryAnaMariaDoroftei.controllers;

import com.demo.onlineLibraryAnaMariaDoroftei.security.SecurityService;
import com.demo.onlineLibraryAnaMariaDoroftei.security.UserPrincipalDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class LoginController {
    private final SecurityService securityService;
    private final UserPrincipalDetailsService userPrincipalDetailsService;

    @PostMapping("/login")
    public Map<String, Object> handleLoginSuccess(@RequestParam String email, @RequestParam String password){
        String authToken = securityService.createAuthToken(email, password);
        UserDetails authenticatedUser = userPrincipalDetailsService.loadUserByUsername(email);

        Map<String, Object> responseParams = new HashMap<>();
        responseParams.put("authToken", authToken);
        responseParams.put("authenticatedUser", authenticatedUser);
        return responseParams;
    }
}
