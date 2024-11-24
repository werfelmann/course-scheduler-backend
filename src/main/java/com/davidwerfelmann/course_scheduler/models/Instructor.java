package com.davidwerfelmann.course_scheduler.models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Instructor  extends AbstractEntity {

    @NotNull
    @Size(min=1, max=50, message = "Name must be between 1 and 50 characters.")
    private String lastName;

    @Size(max=50, message="First name or initial cannot exceed 50 characters.")
    private String firstName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private Boolean isFullTime;

    @ElementCollection(targetClass = Area.class)
    @Enumerated(EnumType.STRING)
    private final Set<Area> academicArea = new HashSet<>();

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private final List<Section> sections = new ArrayList<>();

    public Instructor() {}

    public Instructor(String lastName, String firstName, String email, Boolean isFullTime) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.isFullTime = isFullTime;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getFullTime() {
        return isFullTime;
    }

    public void setFullTime(Boolean fullTime) {
        isFullTime = fullTime;
    }

    public Set<Area> getAcademicArea() {
        return academicArea;
    }

    public void addAcademicArea(Area area) {
        academicArea.add(area);
    }

    public void removeAcademicArea(Area area) {
        academicArea.remove(area);
    }

    public List<Section> getSections() {
        return sections;
    }
}
