package com.maxkors.librebnb.domain;

import com.maxkors.librebnb.infrastructure.RoomRepository;
import com.maxkors.librebnb.infrastructure.UserRepository;
import com.maxkors.librebnb.security.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoomService {

    RoomRepository roomRepository;

    UserRepository userRepository;

    public RoomService(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Autowired


    @Transactional
    public List<Room> getAllRooms() {
        return roomRepository.getAll();
    }

    @Transactional
    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    @Transactional
    public List<Room> getRoomsByCriteria(RoomSearchCriteria roomSearchCriteria) {
        return roomRepository.getByCriteria(roomSearchCriteria);
    }

    @Transactional
    public List<Room> getUsersFavoriteRooms(String username) {
        return roomRepository.getUsersFavouriteRooms(username);
    }

    @Transactional
    public void addRoomToUsersFavorites(String username, Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("Room not found"));
        // TODO: add Optional and method to UserRepository that don't fetch roles eagerly
        User user = userRepository.findByUsername(username);
        user.getFavoriteRooms().add(room);
    }

    @Transactional
    public void removeRoomFromUsersFavorites(String username, Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("Room not found"));
        User user = userRepository.findByUsername(username);
        user.getFavoriteRooms().remove(room);
    }
}
