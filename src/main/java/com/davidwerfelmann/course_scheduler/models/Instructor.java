package com.davidwerfelmann.course_scheduler.models;

import jakarta.persistence.*;
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

}
