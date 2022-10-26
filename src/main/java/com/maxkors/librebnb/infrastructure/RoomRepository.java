package com.maxkors.librebnb.infrastructure;

import com.maxkors.librebnb.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select distinct r from Room r join fetch r.media ")
    List<Room> getAll();
}
