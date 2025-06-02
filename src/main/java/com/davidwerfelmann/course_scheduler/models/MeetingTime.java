package com.davidwerfelmann.course_scheduler.models;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
public class MeetingTime extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    private DaysOfWeek day;

    private LocalTime startTime;
    private LocalTime stopTime;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    public MeetingTime() {};

    public MeetingTime(DaysOfWeek day, LocalTime startTime, LocalTime stopTime, Section section) {
        this.day = day;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.section = section;
    }

    public DaysOfWeek getDay() {
        return day;
    }

    public void setDay(DaysOfWeek day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(LocalTime stopTime) {
        this.stopTime = stopTime;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
