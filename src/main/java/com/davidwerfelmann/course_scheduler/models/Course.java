package com.davidwerfelmann.course_scheduler.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Course extends AbstractEntity {

    private static final String PREFIX = "MUSC";

    @NotNull
    @Pattern (regexp = "\\d{4}", message = "Course number must be a 4-digit number.")
    private String courseNumber;

    @NotNull
    @Size(min=5, max=70, message="Course name must be between 5 and 70 characters.")
    private String name;

    @NotNull
    private int creditHours;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Rotation.class)
    private final Set<Rotation> typicalRotation = new HashSet<>();

    @Size(max=255, message="Note cannot exceed 255 characters.")
    private String notes;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private final List<Section> sections = new ArrayList<>();

    public Course () {};

    public Course(String courseNumber, String name, int creditHours, Set<Rotation> typicalRotation) {
        this.courseNumber = courseNumber;
        this.name = name;
        this.creditHours = creditHours;
        this.typicalRotation.addAll(typicalRotation);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public Set<Rotation> getTypicalRotation() {
        return typicalRotation;
    }

    public void setTypicalRotation(Set<Rotation> typicalRotation) {
        this.typicalRotation.clear();
        this.typicalRotation.addAll(typicalRotation);
    }

    public void addTypicalRotation(Rotation rotation) {
        typicalRotation.add(rotation);
    }

    public void removeTypicalRotation(Rotation rotation) {
        typicalRotation.remove(rotation);
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Section> getSections() {
        return sections;
    }

}
