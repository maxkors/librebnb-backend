package com.maxkors.librebnb.infrastructure;

import com.maxkors.librebnb.domain.BoundingBox;
import com.maxkors.librebnb.domain.Room;
import com.maxkors.librebnb.domain.RoomSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.util.Optional;

//TODO: add @StaticMetamodel for Room
public class RoomSpecification {
    public static Specification<Room> createRoomSpecification(RoomSearchCriteria criteria) {
        BoundingBox bBox = criteria.getBoundingBox();

        return betweenLatitude(bBox.getSWLat(), bBox.getNELat())
                .and(betweenLongitude(bBox.getSWLng(), bBox.getNELng()))
                .and(adultsGreaterThanOrEqualTo(criteria.getAdults()))
                .and(childrenGreaterThanOrEqualTo(criteria.getChildren()))
                .and(petsGreaterThanOrEqualTo(criteria.getPets()));
    }

    private static Specification<Room> betweenLatitude(Double SWLat, Double NELat) {
        return (root, query, criteriaBuilder) -> {
            root.fetch("media", JoinType.LEFT);
            query.distinct(true);
            return criteriaBuilder.between(root.get("latitude"), SWLat, NELat);
        };
    }

    private static Specification<Room> betweenLongitude(Double SWLng, Double NELng) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("longitude"), SWLng, NELng);
    }

    private static Specification<Room> adultsGreaterThanOrEqualTo(Optional<Integer> adults) {
        return (root, query, criteriaBuilder) ->
                adults.map(a -> criteriaBuilder.greaterThanOrEqualTo(root.get("max_adults"), a)).orElse(null);
    }

    private static Specification<Room> childrenGreaterThanOrEqualTo(Optional<Integer> children) {
        return (root, query, criteriaBuilder) ->
                children.map(c -> criteriaBuilder.greaterThanOrEqualTo(root.get("max_adults"), c)).orElse(null);
    }

    private static Specification<Room> petsGreaterThanOrEqualTo(Optional<Integer> pets) {
        return (root, query, criteriaBuilder) ->
                pets.map(p -> criteriaBuilder.greaterThanOrEqualTo(root.get("max_pets"), p)).orElse(null);
    }
}
