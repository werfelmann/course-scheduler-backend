package com.davidwerfelmann.course_scheduler.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
public class Course extends AbstractEntity {

    @NotNull
    @Pattern (regexp = "\\d{4}", message = "Course number must be a 4-digit number.")
    private String courseNumber;

    @NotNull
    @Size(min=5, max=70, message="Course name must be between 5 and 70 characters.")
    private String name;

    @NotNull
    private int creditHours;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Area academicArea;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Area.class)
    @NotNull
    private Set<Rotation> typicalRotation;

    @Size(max=255, message="Note cannot exceed 255 characters.")
    private String notes;

    public @NotNull @Pattern(regexp = "\\d{4}", message = "Course number must be a 4-digit number.") String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(@NotNull @Pattern(regexp = "\\d{4}", message = "Course number must be a 4-digit number.") String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public @NotNull @Size(min = 5, max = 70, message = "Course name must be between 5 and 70 characters.") String getName() {
        return name;
    }

    public void setName(@NotNull @Size(min = 5, max = 70, message = "Course name must be between 5 and 70 characters.") String name) {
        this.name = name;
    }

    @NotNull
    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(@NotNull int creditHours) {
        this.creditHours = creditHours;
    }

    public @NotNull Area getAcademicArea() {
        return academicArea;
    }

    public void setAcademicArea(@NotNull Area academicArea) {
        this.academicArea = academicArea;
    }

    public @NotNull Set<Rotation> getTypicalRotation() {
        return typicalRotation;
    }

    public void setTypicalRotation(@NotNull Set<Rotation> typicalRotation) {
        this.typicalRotation = typicalRotation;
    }

    public @Size(max = 255, message = "Note cannot exceed 255 characters.") String getNotes() {
        return notes;
    }

    public void setNotes(@Size(max = 255, message = "Note cannot exceed 255 characters.") String notes) {
        this.notes = notes;
    }


}
