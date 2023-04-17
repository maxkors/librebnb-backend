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
    private final RoomRepository roomRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void addRoomToUsersFavourites(String username, Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("Room not found"));
        // TODO: add Optional and method to UserRepository that don't fetch roles eagerly
        User user = userRepository.findByUsername(username);
        user.getFavouriteRooms().add(room);
    }
}
