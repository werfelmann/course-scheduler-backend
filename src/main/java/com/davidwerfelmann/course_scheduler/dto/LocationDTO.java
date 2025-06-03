package com.davidwerfelmann.course_scheduler.dto;

public class LocationDTO {
    private Long id;
    private String building;
    private String roomNumber;

    public LocationDTO() {}

    public LocationDTO(Long id, String building, String roomNumber) {
        this.id = id;
        this.building = building;
        this.roomNumber = roomNumber;
    }

    public Long getId() {
        return id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
