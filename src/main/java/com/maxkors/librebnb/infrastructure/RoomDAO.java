package com.maxkors.librebnb.infrastructure;

import com.maxkors.librebnb.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDAO extends JpaRepository<Room, Long> {
}
