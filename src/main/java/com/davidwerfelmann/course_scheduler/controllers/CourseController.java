package com.davidwerfelmann.course_scheduler.controllers;

import com.davidwerfelmann.course_scheduler.data.CourseRepository;
import com.davidwerfelmann.course_scheduler.models.Course;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<Course> getAllCourses() {
        List<Course> courseList = (List<Course>) courseRepository.findAll();
        return courseList;
    }

    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable Integer courseId) {
        Optional<Course> optCourse = courseRepository.findById(courseId);

        if (optCourse.isPresent()) {
            Course course = (Course) optCourse.get();
            return course;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found.");
        }

    }

    @PostMapping
    public Course createCourse(@RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Validation failed");
        }

        Course newCourse = courseRepository.save(course);
        return newCourse;
    }
}
