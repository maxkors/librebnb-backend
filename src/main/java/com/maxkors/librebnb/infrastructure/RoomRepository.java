package com.maxkors.librebnb.infrastructure;

import com.maxkors.librebnb.domain.Room;
import com.maxkors.librebnb.domain.RoomSearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {

    @Query("select distinct r from Room r join fetch r.media ")
    List<Room> getAll();

//    @EntityGraph(
//            type = EntityGraph.EntityGraphType.FETCH,
//            attributePaths = {
//                    "media"
//            }
//    )
    default List<Room> getByCriteria(RoomSearchCriteria criteria) {
        Specification<Room> roomSpecification = RoomSpecification.createRoomSpecification(criteria);
        return findAll(roomSpecification);
    }
}
