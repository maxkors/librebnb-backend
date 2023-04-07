package com.maxkors.librebnb.api;

import com.maxkors.librebnb.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public Profile getProfile(@AuthenticationPrincipal User principal) {
        com.maxkors.librebnb.security.User user = userService.getUserByUsername(principal.getUsername());
        return new Profile(user.getUsername(), user.getEmail(), user.getPhoneNumber());
    }

    record Profile(String username, String email, String phone_number) {
    }
}
