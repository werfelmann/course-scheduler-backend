package com.davidwerfelmann.course_scheduler.dto;

import java.util.List;

public class SemesterScheduleDTO {

    private String semester;
    private int year;
    private List<SectionDTO> sections;

    public SemesterScheduleDTO() {}

    public SemesterScheduleDTO(String semester, int year, List<SectionDTO> sections) {
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

    public List<SectionDTO> getSections() {
        return sections;
    }

    public void setSections(List<SectionDTO> sections) {
        this.sections = sections;
    }
}
