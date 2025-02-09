package com.davidwerfelmann.course_scheduler.mapper;

import com.davidwerfelmann.course_scheduler.dto.*;
import com.davidwerfelmann.course_scheduler.models.*;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class SemesterScheduleMapper {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");

    public static SemesterScheduleDTO toDTO(SemesterSchedule semesterSchedule) {
        return new SemesterScheduleDTO(
                semesterSchedule.getSemester(),
                semesterSchedule.getYear(),
                semesterSchedule.getSections().stream()
                        .map(SemesterScheduleMapper::toDTO)
                        .collect(Collectors.toList())
        );
    }

    public static SectionDTO toDTO(Section section) {
        return new SectionDTO(
                section.getSectionNumber(),
                toDTO(section.getCourse()),
                toDTO(section.getInstructor()),
                section.getLocation() != null ? toDTO(section.getLocation()) : null,
                section.getAcademicArea(),
                section.getStartTime() != null ? section.getStartTime().format(TIME_FORMATTER) : "Not Scheduled",
                section.getStopTime() != null ? section.getStopTime().format(TIME_FORMATTER) : "Not Scheduled",
                section.isOnline()
        );
    }

    private static CourseDTO toDTO(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getCourseNumber(),
                course.getMinCreditHours(),
                course.getMaxCreditHours(),
                course.getTypicalRotation()
        );
    }

    private static InstructorDTO toDTO(Instructor instructor) {
        return new InstructorDTO(
                instructor.getFirstName(),
                instructor.getLastName()
        );
    }

    private static LocationDTO toDTO(Location location) {
        return new LocationDTO(
                location.getBuilding(),
                location.getRoom()
        );
    }
}
