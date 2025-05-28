package com.davidwerfelmann.course_scheduler.data;

import com.davidwerfelmann.course_scheduler.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Integer> {
}
