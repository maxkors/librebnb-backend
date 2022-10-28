package com.maxkors.librebnb.api;

import com.maxkors.librebnb.domain.BoundingBox;
import com.maxkors.librebnb.domain.Room;
import com.maxkors.librebnb.domain.RoomSearchCriteria;
import com.maxkors.librebnb.domain.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {

    RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms-all")
    List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    //TODO:  validation
    //TODO: limit quantity (pagination/SQL)
    @GetMapping("/rooms")
    List<Room> getRoomsByCriteria(
            @RequestParam("ne_lat") Double NELat,
            @RequestParam("ne_lng") Double NELng,
            @RequestParam("sw_lat") Double SWLat,
            @RequestParam("sw_lng") Double SWLng,

            @RequestParam("checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkin,
            @RequestParam("checkout") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkout,

            @RequestParam("adults") Optional<Integer> adults,
            @RequestParam("children") Optional<Integer> children,
            @RequestParam("pets") Optional<Integer> pets,

            //TODO: should be enum
            @RequestParam("amenities") Optional<List<String>> amenities
    ) {
        var boundingBox = new BoundingBox(NELat, NELng, SWLat, SWLng);
        System.out.println(boundingBox);
        var roomSearchCriteria = new RoomSearchCriteria(boundingBox, adults, children, pets);

        return roomService.getRoomsByCriteria(roomSearchCriteria);
    }
}
