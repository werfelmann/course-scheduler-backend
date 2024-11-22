package com.davidwerfelmann.course_scheduler.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Room extends AbstractEntity {

    @NotNull
    @Size(min = 1, max = 4, message = "Building name must be between 1 and 4 characters.")
    private String building;

    private String roomNumber;

    private int capacity;

    private List<Section> sections = new ArrayList<>();

}
