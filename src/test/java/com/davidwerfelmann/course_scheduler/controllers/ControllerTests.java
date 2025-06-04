package com.davidwerfelmann.course_scheduler.controllers;

import com.davidwerfelmann.course_scheduler.dto.CourseDTO;
import com.davidwerfelmann.course_scheduler.dto.CreateSectionDTO;
import com.davidwerfelmann.course_scheduler.models.Area;
import com.davidwerfelmann.course_scheduler.models.Rotation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createSection_shouldReturn201_whenValidInput() throws Exception {
        CreateSectionDTO dto = new CreateSectionDTO();
        dto.setSectionNumber(1);
        dto.setCourseId(1);
        dto.setInstructorId(1);
        dto.setLocationId(1);
        dto.setSemesterScheduleId(1);
        dto.setAcademicArea(Area.THEORY);
        dto.setStartTime("09:00");
        dto.setStopTime("09:50");
        dto.setOnline(false);

        mockMvc.perform(post("/api/sections")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createCourse_shouldReturn201_whenValidInput() throws Exception {
        CourseDTO dto = new CourseDTO();
        Set<Rotation> rotationSet = new HashSet<>();
        rotationSet.add(Rotation.FA);
        rotationSet.add(Rotation.SP);

        dto.setCourseNumber("0101");
        dto.setName("Test Course");
        dto.setMinCreditHours(0);
        dto.setMaxCreditHours(3);
        dto.setTypicalRotation(rotationSet);


        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }
}
