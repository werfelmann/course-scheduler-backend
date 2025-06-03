package com.davidwerfelmann.course_scheduler.mapper;

import com.davidwerfelmann.course_scheduler.dto.*;
import com.davidwerfelmann.course_scheduler.models.*;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class DTOMapper {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");

    public static SemesterScheduleDTO semesterScheduletoToDTO(SemesterSchedule semesterSchedule) {
        return new SemesterScheduleDTO(
                semesterSchedule.getId(),
                semesterSchedule.getSemester(),
                semesterSchedule.getYear(),
                semesterSchedule.getSections().stream()
                        .map(DTOMapper::sectionToDTO)
                        .collect(Collectors.toList())
        );
    }

    public static SectionDTO sectionToDTO(Section section) {
        return new SectionDTO(
                section.getId(),
                section.getSectionNumber(),
                DTOMapper.courseToDTO(section.getCourse()),
                DTOMapper.instructorToDTO(section.getInstructor()),
                DTOMapper.locationToDTO(section.getLocation()),
                section.getAcademicArea(),
                section.getStartTime().toString(),
                section.getStopTime().toString(),
                section.isOnline(),
                section.getSemesterSchedule()
        );
    }

    public static CourseDTO courseToDTO(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getCourseNumber(),
                course.getMinCreditHours(),
                course.getMaxCreditHours(),
                course.getTypicalRotation(),
                course.getDescription(),
                course.getNotes()
        );
    }

    public static InstructorDTO instructorToDTO(Instructor instructor) {
        return new InstructorDTO(
                instructor.getId(),
                instructor.getFirstName(),
                instructor.getLastName()
        );
    }

    public static LocationDTO locationToDTO(Location location) {
        return new LocationDTO(
                location.getId(),
                location.getBuilding(),
                location.getRoom()
        );
    }
}
