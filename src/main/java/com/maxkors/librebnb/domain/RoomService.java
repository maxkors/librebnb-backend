package com.maxkors.librebnb.domain;

import com.maxkors.librebnb.infrastructure.RoomDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoomService {

    RoomDAO roomDAO;

    @Autowired
    public RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Transactional
    public List<Room> getAllRooms() {
        return roomDAO.findAll();
    }
}
