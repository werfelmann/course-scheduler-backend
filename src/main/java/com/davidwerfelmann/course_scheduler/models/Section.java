package com.davidwerfelmann.course_scheduler.models;

import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    @NotNull
    private Area academicArea;

    @NotNull
    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private LocalTime startTime;

    private LocalTime stopTime;

    private boolean isOnline = false;

    @ManyToOne
    @JoinColumn(name = "semester_schedule_id")
    private SemesterSchedule semesterSchedule;

    public Section() {}

    public Section(Course course, int sectionNumber, Area academicArea, Instructor instructor, Location location, LocalTime startTime, LocalTime stopTime, SemesterSchedule semesterSchedule) {
        this.course = course;
        this.sectionNumber = sectionNumber;
        this.academicArea = academicArea;
        this.instructor = instructor;
        this.location = location;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.isOnline = false;
        setSemesterSchedule(semesterSchedule);
    }

    public Section(Course course, int sectionNumber, Instructor instructor, boolean isOnline) {
        this.course = course;
        this.sectionNumber = sectionNumber;
        this.instructor = instructor;
        this.isOnline = isOnline;
    }

    @AssertTrue(message = "Start time must be earlier than stop time.")
    public boolean isValidTimeRange() {
        // Null values are allowed for unscheduled times
        if (startTime == null || stopTime == null) {
            return true; // Skip validation if either time is null
        }
        return stopTime.isAfter(startTime);
    }

    @AssertTrue(message = "Both start and stop times must be set together or left blank.")
    public boolean isTimeConsistencyValid() {
        return (startTime == null && stopTime == null) || (startTime != null && stopTime != null);
    }

    @AssertTrue(message = "Online sections should not have a physical location.")
    public boolean isLocationValid() {
        return !isOnline || location == null;
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

    public Area getAcademicArea() {
        return academicArea;
    }

    public void setAcademicArea(Area academicArea) {
        this.academicArea = academicArea;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public SemesterSchedule getSemesterSchedule() {
        return semesterSchedule;
    }

    public void setSemesterSchedule(SemesterSchedule semesterSchedule) {
        this.semesterSchedule = semesterSchedule;
        if (semesterSchedule != null && !semesterSchedule.getSections().contains(this)) {
            semesterSchedule.addSection(this);
        }
    }

    @Override
    public String toString() {
        return course.getName() + " " + sectionNumber;
    }
}
