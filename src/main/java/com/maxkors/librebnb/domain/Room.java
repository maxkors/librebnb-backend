package com.maxkors.librebnb.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {

    @Id
    @SequenceGenerator(name = "room_seq_gen", sequenceName = "room_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_seq_gen")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "max_adults")
    private Long max_adults;

    @Column(name = "max_children")
    private Long max_children;

    @Column(name = "max_pets")
    private Long max_pets;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Media> media;

    public Room() {
    }

    public Room(String description, Long price, Double latitude, Double longitude, Long max_adults, Long max_children, Long max_pets) {
        this.description = description;
        this.price = price;
        this.latitude = latitude;
        this.longitude = longitude;
        this.max_adults = max_adults;
        this.max_children = max_children;
        this.max_pets = max_pets;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getMax_adults() {
        return max_adults;
    }

    public void setMax_adults(Long max_adults) {
        this.max_adults = max_adults;
    }

    public Long getMax_children() {
        return max_children;
    }

    public void setMax_children(Long max_children) {
        this.max_children = max_children;
    }

    public Long getMax_pets() {
        return max_pets;
    }

    public void setMax_pets(Long max_pets) {
        this.max_pets = max_pets;
    }
}
