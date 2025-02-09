package com.davidwerfelmann.course_scheduler.dto;

public class LocationDTO {
    private String building;
    private String roomNumber;

    public LocationDTO() {}

    public LocationDTO(String building, String roomNumber) {
        this.building = building;
        this.roomNumber = roomNumber;
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
