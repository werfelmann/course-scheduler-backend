package com.davidwerfelmann.course_scheduler.data;

import com.davidwerfelmann.course_scheduler.models.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
