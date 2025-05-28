package com.davidwerfelmann.course_scheduler.data;

import com.davidwerfelmann.course_scheduler.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
