package com.maxkors.librebnb.infrastructure;

import com.maxkors.librebnb.domain.Room;
import com.maxkors.librebnb.domain.RoomSearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query(value = "select fr from User u left join u.favoriteRooms fr left join fetch fr.media m where u.username = :username")
    List<Room> getUsersFavouriteRooms(@Param("username") String username);
}
