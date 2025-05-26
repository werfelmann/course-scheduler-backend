package com.davidwerfelmann.course_scheduler.data;

import com.davidwerfelmann.course_scheduler.models.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
}
