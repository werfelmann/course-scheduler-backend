package com.davidwerfelmann.course_scheduler.dto;

import com.davidwerfelmann.course_scheduler.models.Area;

public class CreateSectionDTO {
    private int sectionNumber;
    private int courseId;
    private int instructorId;
    private int locationId;
    private Area academicArea;
    private String startTime;
    private String stopTime;
    private boolean isOnline = false;
    private int semesterScheduleId;

    public CreateSectionDTO() {}

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public int getSemesterScheduleId() {
        return semesterScheduleId;
    }

    public void setSemesterScheduleId(int semesterScheduleId) {
        this.semesterScheduleId = semesterScheduleId;
    }

}
