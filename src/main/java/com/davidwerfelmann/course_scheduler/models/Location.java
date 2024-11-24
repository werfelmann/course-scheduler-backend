package com.davidwerfelmann.course_scheduler.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"building", "room"}))
public class Location extends AbstractEntity {

    @NotNull
    @Size(min = 1, max = 4, message = "Building name must be between 1 and 4 characters.")
    private String building;

    @NotNull
    @Size(min = 1, max = 4, message = "Room number must be between 1 and 4 characters.")
    private String room;

    @Min(value = 1, message = "Capacity must be at least 1.")
    private int capacity;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Section> sections = new ArrayList<>();

    public Location() {}

    public Location(String building, String room, int capacity) {
        this.building = building;
        this.room = room;
        this.capacity = capacity;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String roomNumber) {
        this.room = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void addSection(Section section) {
        if (section != null) {
            sections.add(section);
            section.setLocation(this);
        }
    }

    public String getFullRoomName() {
        return building + " " + room;
    }

    @Override
    public String toString() {
        return getFullRoomName();
    }
}
