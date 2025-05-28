package com.davidwerfelmann.course_scheduler.controllers;

import com.davidwerfelmann.course_scheduler.data.CourseRepository;
import com.davidwerfelmann.course_scheduler.dto.CourseDTO;
import com.davidwerfelmann.course_scheduler.mapper.DTOMapper;
import com.davidwerfelmann.course_scheduler.models.Course;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/courses")
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<Course> courseList = (List<Course>) courseRepository.findAll();
        List<CourseDTO> courseDTOList = courseList.stream()
                .map(DTOMapper::courseToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(courseDTOList);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long courseId) {
        Optional<Course> optCourse = courseRepository.findById(courseId);
        if (optCourse.isPresent()) {
            Course course = optCourse.get();
            CourseDTO courseDTO = DTOMapper.courseToDTO(course);
            return ResponseEntity.ok(courseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        try {
            courseRepository.delete(course);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot delete course with dependent records");
        }
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody @Valid CourseDTO courseDTO, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        if (courseDTO.getMaxCreditHours() < courseDTO.getMinCreditHours()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        if (courseRepository.existsByCourseNumber(courseDTO.getCourseNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .build();
        }

        Course newCourse = new Course();
        newCourse.setName(courseDTO.getName());
        newCourse.setCourseNumber(courseDTO.getCourseNumber());
        newCourse.setMinCreditHours(courseDTO.getMinCreditHours());
        newCourse.setMaxCreditHours(courseDTO.getMaxCreditHours());
        newCourse.setTypicalRotation(courseDTO.getTypicalRotation());

        Course savedCourse = courseRepository.save(newCourse);
        CourseDTO responseDTO = DTOMapper.courseToDTO(savedCourse);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long courseId, @RequestBody @Valid CourseDTO courseDTO, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Course existingCourse = optionalCourse.get();
        existingCourse.setName(courseDTO.getName());
        existingCourse.setCourseNumber(courseDTO.getCourseNumber());
        existingCourse.setMinCreditHours(courseDTO.getMinCreditHours());
        existingCourse.setMaxCreditHours(courseDTO.getMaxCreditHours());
        existingCourse.setTypicalRotation(courseDTO.getTypicalRotation());

        if (existingCourse.getMaxCreditHours() < existingCourse.getMinCreditHours()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Course savedCourse = courseRepository.save(existingCourse);
        CourseDTO responseDTO = new CourseDTO(
                savedCourse.getId(),
                savedCourse.getName(),
                savedCourse.getCourseNumber(),
                savedCourse.getMinCreditHours(),
                savedCourse.getMaxCreditHours(),
                savedCourse.getTypicalRotation());

        return ResponseEntity.ok(responseDTO);
    }
}
