package com.maxkors.librebnb.domain;

import java.util.Optional;

public class RoomSearchCriteria {

    private BoundingBox boundingBox;
    private Optional<Integer> adults;
    private Optional<Integer> children;
    private Optional<Integer> pets;

    public RoomSearchCriteria(BoundingBox boundingBox, Optional<Integer> adults, Optional<Integer> children, Optional<Integer> pets) {
        this.boundingBox = boundingBox;
        this.adults = adults;
        this.children = children;
        this.pets = pets;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public Optional<Integer> getAdults() {
        return adults;
    }

    public void setAdults(Optional<Integer> adults) {
        this.adults = adults;
    }

    public Optional<Integer> getChildren() {
        return children;
    }

    public void setChildren(Optional<Integer> children) {
        this.children = children;
    }

    public Optional<Integer> getPets() {
        return pets;
    }

    public void setPets(Optional<Integer> pets) {
        this.pets = pets;
    }
}
