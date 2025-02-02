package com.davidwerfelmann.course_scheduler.dto;

import com.davidwerfelmann.course_scheduler.models.Rotation;

import java.util.Set;

public class CourseDTO {
    private Long id;
    private String name;
    private String courseNumber;
    private String fullCourseNumber;
    private int minCreditHours;
    private int maxCreditHours;
    private Set<Rotation> typicalRotation;
    private static final String PREFIX = "MUSC";

    public CourseDTO(Long id, String name, String courseNumber, int minCreditHours, int maxCreditHours, Set<Rotation> typicalRotation) {
        this.id = id;
        this.name = name;
        this.courseNumber = courseNumber;
        this.fullCourseNumber = PREFIX + " " + courseNumber;
        this.minCreditHours = minCreditHours;
        this.maxCreditHours = maxCreditHours;
        this.typicalRotation = typicalRotation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getFullCourseNumber() {
        return PREFIX + " " + courseNumber;
    }

    public int getMinCreditHours() {
        return minCreditHours;
    }

    public void setMinCreditHours(int minCreditHours) {
        this.minCreditHours = minCreditHours;
    }

    public int getMaxCreditHours() {
        return maxCreditHours;
    }

    public void setMaxCreditHours(int maxCreditHours) {
        this.maxCreditHours = maxCreditHours;
    }

    public Set<Rotation> getTypicalRotation() {
        return typicalRotation;
    }

    public void setTypicalRotation(Set<Rotation> typicalRotation) {
        this.typicalRotation = typicalRotation;
    }

    public String getFormattedCreditHours() {
        return (minCreditHours == maxCreditHours) ? String.valueOf(minCreditHours) : (minCreditHours + "-" + maxCreditHours);
    }
}
