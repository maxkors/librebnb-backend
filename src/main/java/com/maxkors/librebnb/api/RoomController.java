package com.maxkors.librebnb.api;

import com.maxkors.librebnb.domain.BoundingBox;
import com.maxkors.librebnb.domain.Room;
import com.maxkors.librebnb.domain.RoomSearchCriteria;
import com.maxkors.librebnb.domain.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    //TODO:  validation
    //TODO: limit quantity (pagination/SQL)
    @GetMapping
    List<Room> getRoomsByCriteria(@RequestParam("ne_lat") Double NELat,
                                  @RequestParam("ne_lng") Double NELng,
                                  @RequestParam("sw_lat") Double SWLat,
                                  @RequestParam("sw_lng") Double SWLng,

                                  @RequestParam("checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkin,
                                  @RequestParam("checkout") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkout,

                                  @RequestParam("adults") Optional<Integer> adults,
                                  @RequestParam("children") Optional<Integer> children,
                                  @RequestParam("pets") Optional<Integer> pets,

                                  //TODO: should be enum
                                  @RequestParam("amenities") Optional<List<String>> amenities) {
        var boundingBox = new BoundingBox(NELat, NELng, SWLat, SWLng);
        System.out.println(boundingBox);
        var roomSearchCriteria = new RoomSearchCriteria(boundingBox, adults, children, pets);

        return roomService.getRoomsByCriteria(roomSearchCriteria);
    }

    @GetMapping("/all")
    List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/favourite")
    public List<Room> getFavouriteRooms(@AuthenticationPrincipal User principal) {
        List<Room> favouriteRooms = roomService.getUsersFavouriteRooms(principal.getUsername());
        return favouriteRooms;
    }
}
