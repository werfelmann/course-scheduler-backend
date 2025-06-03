package com.davidwerfelmann.course_scheduler.controllers;

import com.davidwerfelmann.course_scheduler.data.SemesterScheduleRepository;
import com.davidwerfelmann.course_scheduler.dto.SemesterScheduleDTO;
import com.davidwerfelmann.course_scheduler.mapper.DTOMapper;
import com.davidwerfelmann.course_scheduler.models.SemesterSchedule;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/schedules")
@CrossOrigin(origins = "http://localhost:5173")
public class ScheduleController {

    @Autowired
    private SemesterScheduleRepository semesterScheduleRepository;

    @GetMapping
    public ResponseEntity<List<SemesterScheduleDTO>> getAllSchedules() {
        List<SemesterSchedule> semesterSchedules = semesterScheduleRepository.findAll();

        if (semesterSchedules.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<SemesterScheduleDTO> semesterScheduleDTOList = semesterSchedules.stream()
                .map(DTOMapper::semesterScheduletoToDTO)
                .toList();
        return ResponseEntity.ok(semesterScheduleDTOList);
    }

    @PostMapping
    public ResponseEntity<SemesterScheduleDTO> createSemesterSchedule(@RequestBody @Valid SemesterScheduleDTO semesterScheduleDTO, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (semesterScheduleRepository.existsByYearAndSemester(semesterScheduleDTO.getYear(), semesterScheduleDTO.getSemester())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        SemesterSchedule newSchedule = new SemesterSchedule();
        newSchedule.setSemester(semesterScheduleDTO.getSemester());
        newSchedule.setYear(semesterScheduleDTO.getYear());

        semesterScheduleRepository.save(newSchedule);

        SemesterScheduleDTO responseDTO = DTOMapper.semesterScheduletoToDTO(newSchedule);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }
}
