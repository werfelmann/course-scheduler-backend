package com.davidwerfelmann.course_scheduler.dto;

import com.davidwerfelmann.course_scheduler.models.Area;

public class SectionDTO {

    private int sectionNumber;
    private CourseDTO course;
    private InstructorDTO instructor;
    private LocationDTO location;
    private Area academicArea;
    private String startTime;
    private String stopTime;
    private Boolean isOnline;

    public SectionDTO() {}

    public SectionDTO(int sectionNumber, CourseDTO course, InstructorDTO instructor, LocationDTO location, Area academicArea, String startTime, String stopTime, Boolean isOnline) {
        this.sectionNumber = sectionNumber;
        this.course = course;
        this.instructor = instructor;
        this.location = location;
        this.academicArea = academicArea;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.isOnline = isOnline;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public InstructorDTO getInstructor() {
        return instructor;
    }

    public void setInstructor(InstructorDTO instructor) {
        this.instructor = instructor;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public Area getAcademicArea() {
        return academicArea;
    }

    public void setAcademicArea(Area academicArea) {
        this.academicArea = academicArea;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public Boolean isOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    @Override
    public String toString() {
        return "SectionDTO{" +
                "sectionNumber=" + sectionNumber +
                ", course=" + (course != null ? course.getFullCourseNumber() : "N/A") +
                ", instructor=" + (instructor != null ? instructor.getName() : "N/A") +
                ", location=" + (location != null ? location.getBuilding() + " " + location.getRoomNumber() : "Online") +
                ", academicArea=" + academicArea +
                ", startTime='" + startTime + '\'' +
                ", stopTime='" + stopTime + '\'' +
                ", isOnline=" + isOnline +
                '}';
    }

}
