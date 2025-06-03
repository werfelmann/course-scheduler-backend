package com.davidwerfelmann.course_scheduler.dto;

import com.davidwerfelmann.course_scheduler.models.Semester;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class SemesterScheduleDTO {

    private Long id;

    @NotNull
    private Semester semester;

    @Min(2000)
    @Max(2100)
    private int year;
    private List<SectionDTO> sections;

    public SemesterScheduleDTO() {}

    public SemesterScheduleDTO(Long id, Semester semester, int year, List<SectionDTO> sections) {
        this.id = id;
        this.semester = semester;
        this.year = year;
        this.sections = sections;
    }

    public Long getId() {
        return id;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<SectionDTO> getSections() {
        return sections;
    }

    public void setSections(List<SectionDTO> sections) {
        this.sections = sections;
    }
}
