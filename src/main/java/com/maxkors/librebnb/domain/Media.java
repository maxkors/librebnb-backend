package com.maxkors.librebnb.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Media {

    @Id
    @SequenceGenerator(name = "media_seq_gen", sequenceName = "room_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "media_seq_gen")
    private Long id;

    @Column(name = "file_name")
    private String filename;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "room_id")
    @JsonBackReference
    private Room room;

    public Media() {
    }

    public Media(String filename, Room room) {
        this.filename = filename;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", room=" + room +
                '}';
    }
}
