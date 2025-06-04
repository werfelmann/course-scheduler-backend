package com.davidwerfelmann.course_scheduler.controllers;

import com.davidwerfelmann.course_scheduler.data.*;
import com.davidwerfelmann.course_scheduler.dto.InstructorDTO;
import com.davidwerfelmann.course_scheduler.dto.SectionDTO;
import com.davidwerfelmann.course_scheduler.mapper.DTOMapper;
import com.davidwerfelmann.course_scheduler.models.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/sections")
public class SectionController {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SemesterScheduleRepository semesterScheduleRepository;

    @GetMapping
    public ResponseEntity<List<SectionDTO>> getAllSections() {
        List<Section> sectionList = (List<Section>) sectionRepository.findAll();
        List<SectionDTO> sectionDTOList = sectionList.stream()
        .map(DTOMapper::sectionToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sectionDTOList);
    }

    @GetMapping("/{sectionId}")
    public Section getSectionById(@PathVariable Integer sectionId) {
        Optional<Section> optSection = sectionRepository.findById(sectionId);

        if (optSection.isPresent()) {
            Section section = (Section) optSection.get();
            return section;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Validation failed");
        }
    }

    @PostMapping
    public ResponseEntity<?> createSection(@RequestBody @Valid SectionDTO sectionDTO, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        Course course = courseRepository.findById(sectionDTO.getCourse().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course not found"));

        Instructor instructor = instructorRepository.findById(sectionDTO.getInstructor().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Instructor not found"));

        Location location = locationRepository.findById(sectionDTO.getLocation().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location not found"));

        SemesterSchedule semesterSchedule = semesterScheduleRepository.findById(sectionDTO.getSemesterSchedule().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Semester schedule not found"));


        Section newSection = new Section();
        newSection.setCourse(course);
        newSection.setSectionNumber(sectionDTO.getSectionNumber());
        newSection.setAcademicArea(sectionDTO.getAcademicArea());
        newSection.setInstructor(instructor);
        newSection.setLocation(location);
        newSection.setStartTime(LocalTime.parse(sectionDTO.getStartTime()));
        newSection.setStopTime(LocalTime.parse(sectionDTO.getStopTime()));
        newSection.setOnline(sectionDTO.isOnline());
        newSection.setSemesterSchedule(semesterSchedule);

        sectionRepository.save(newSection);
        SectionDTO responseDTO = DTOMapper.sectionToDTO(newSection);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }
}
