package com.maxkors.librebnb.api;

import com.maxkors.librebnb.domain.Room;
import com.maxkors.librebnb.domain.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {

    RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
}
