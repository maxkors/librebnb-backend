package com.maxkors.librebnb.domain;

import com.maxkors.librebnb.infrastructure.RoomRepository;
import com.maxkors.librebnb.infrastructure.UserRepository;
import com.maxkors.librebnb.security.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
