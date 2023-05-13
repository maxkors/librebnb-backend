package com.maxkors.librebnb.api;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.maxkors.librebnb.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    RoomService roomService;
    UserService userService;

    @Autowired
    public RoomController(RoomService roomService, UserService userService) {
        this.roomService = roomService;
        this.userService = userService;
    }

    //TODO:  validation
    //TODO: limit quantity (pagination/SQL)
    @GetMapping
    List<FavoriteRoom> getRoomsByCriteria(@AuthenticationPrincipal User principal,

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
                                          @RequestParam("amenities") Optional<List<String>> amenities) {
        System.out.println(principal);

        var boundingBox = new BoundingBox(NELat, NELng, SWLat, SWLng);
        System.out.println(boundingBox);
        var roomSearchCriteria = new RoomSearchCriteria(boundingBox, adults, children, pets);

        List<Room> searchedRooms = roomService.getRoomsByCriteria(roomSearchCriteria);

        if (principal == null) {
            return searchedRooms.stream().map(room -> new FavoriteRoom(room, false)).toList();
        }

        Set<Room> usersFavoriteRooms = new HashSet<>(roomService.getUsersFavoriteRooms(principal.getUsername()));

        return searchedRooms.stream().map(room -> new FavoriteRoom(room, usersFavoriteRooms.contains(room))).toList();
    }

    @GetMapping("/{id}")
        // TODO: replace db id with uuid
    ResponseEntity<Room> getRoomById(@PathVariable("id") Long id) {
        return ResponseEntity.of(roomService.getRoomById(id));
    }

    @GetMapping("/all")
    List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/favorite")
    public List<FavoriteRoom> getFavoriteRooms(@AuthenticationPrincipal User principal) {
        List<Room> favoriteRooms = roomService.getUsersFavoriteRooms(principal.getUsername());
        return favoriteRooms.stream().map(room -> new FavoriteRoom(room, true)).toList();
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<String> addRoomToUsersFavorites(@PathVariable("id") Long roomId, @AuthenticationPrincipal User principal) {
        roomService.addRoomToUsersFavorites(principal.getUsername(), roomId);

        return ResponseEntity.ok("Room added to favourites");
    }

    @DeleteMapping("/{id}/like")
    public ResponseEntity<String> removeRoomFromUsersFavorites(@PathVariable("id") Long roomId, @AuthenticationPrincipal User principal) {
        roomService.removeRoomFromUsersFavorites(principal.getUsername(), roomId);

        return ResponseEntity.ok("Room removed from favorites");
    }

    public record FavoriteRoom(@JsonUnwrapped Room room, boolean isLiked) {
    }
}
