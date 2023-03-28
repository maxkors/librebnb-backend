package com.maxkors.librebnb.api;

import com.maxkors.librebnb.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/profile")
public class ProfileController {

    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @Transactional
    Profile getProfile(@AuthenticationPrincipal User principal) {
        com.maxkors.librebnb.security.User user = userRepository.findByUsername(principal.getUsername());
        return new Profile(user.getUsername());
    }

    record Profile(String username) {}
}
