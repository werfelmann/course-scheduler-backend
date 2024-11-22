package com.davidwerfelmann.course_scheduler.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Section extends AbstractEntity {

    @NotNull
    @Min(value = 1, message="Section number must be at least 1.")
    @Max(value=99, message="Section number must be less than 100.")
    private int sectionNumber;

    @NotNull
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    @NotNull
    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    @Size(min=1, max=4, message = "Building must be between 1 and 4 characters.")
    private String building;

    @Size(min=1, max=4, message = "Room number must be between 1 and 4 characters.")
    private String roomNumber;

    private LocalTime startTime;

    private LocalTime stopTime;

    private boolean isOnline = false;

    public Section() {}

    public Section(int sectionNumber, Course course, Instructor instructor, String building, String roomNumber, LocalTime startTime, LocalTime stopTime) {
        this.sectionNumber = sectionNumber;
        this.course = course;
        this.instructor = instructor;
        this.building = building;
        this.roomNumber = roomNumber;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    public Section(int sectionNumber, Course course, Instructor instructor, String building, String roomNumber, LocalTime startTime, LocalTime stopTime, boolean isOnline) {
        this.sectionNumber = sectionNumber;
        this.course = course;
        this.instructor = instructor;
        this.building = building;
        this.roomNumber = roomNumber;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.isOnline = isOnline;
    }

    @AssertTrue(message = "Stop time must be after start time.")
    public boolean isValidTimeRange() {
        // Null values are allowed for unscheduled times
        if (startTime == null || stopTime == null) {
            return true; // Skip validation if either time is null
        }
        return stopTime.isAfter(startTime);
    }

    @AssertTrue(message = "Both start and stop times must be set together.")
    public boolean isTimeConsistencyValid() {
        return (startTime == null && stopTime == null) || (startTime != null && stopTime != null);
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public String getFormattedStartTime() {
        return startTime != null ? startTime.format(DateTimeFormatter.ofPattern("hh:mm a")) : "Not Scheduled";
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getStopTime() {
        return stopTime;
    }

    public String getFormattedStopTime() {
        return stopTime != null ? stopTime.format(DateTimeFormatter.ofPattern("hh:mm a")) : "Not Scheduled";
    }

    public void setStopTime(LocalTime stopTime) {
        this.stopTime = stopTime;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
