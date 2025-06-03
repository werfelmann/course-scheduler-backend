package com.davidwerfelmann.course_scheduler.data;

import com.davidwerfelmann.course_scheduler.models.Semester;
import com.davidwerfelmann.course_scheduler.models.SemesterSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterScheduleRepository extends JpaRepository<SemesterSchedule, Long> {

    boolean existsByYearAndSemester(int year, Semester semester);

}
