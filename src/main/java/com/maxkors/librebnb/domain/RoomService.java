package com.maxkors.librebnb.domain;

import com.maxkors.librebnb.infrastructure.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional
    public List<Room> getAllRooms() {
        return roomRepository.getAll();
    }

    @Transactional
    public List<Room> getRoomsByCriteria(RoomSearchCriteria roomSearchCriteria) {
        return roomRepository.getByCriteria(roomSearchCriteria);
    }

    @Transactional
    public List<Room> getUsersFavouriteRooms(String username) {
        return roomRepository.getUsersFavouriteRooms(username);
    }
}
