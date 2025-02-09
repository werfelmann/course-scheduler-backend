package com.davidwerfelmann.course_scheduler.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SemesterSchedule extends AbstractEntity {

    @NotNull
    private String semester;

    @NotNull
    private int year;

    @OneToMany(mappedBy = "semesterSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Department department;

    public SemesterSchedule() {};

    public SemesterSchedule(String semester, int year, List<Section> sections) {
        this.semester = semester;
        this.year = year;
        this.sections = sections;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section) {
        this.sections.add(section);
        section.setSemesterSchedule(this);
    }

    public void removeSection(Section section) {
        this.sections.remove(section);
        section.setSemesterSchedule(null);
    }

    @Override
    public String toString() {
        return "SemesterSchedule{" +
                "semester='" + semester + '\'' +
                ", year=" + year +
                '}';
    }
}
