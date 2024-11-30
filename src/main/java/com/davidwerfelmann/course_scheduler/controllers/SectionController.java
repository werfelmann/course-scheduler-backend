package com.davidwerfelmann.course_scheduler.controllers;

import com.davidwerfelmann.course_scheduler.data.SectionRepository;
import com.davidwerfelmann.course_scheduler.models.Section;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/sections")
public class SectionController {

    @Autowired
    private SectionRepository sectionRepository;

    @GetMapping
    public List<Section> getAllSections() {
        List<Section> sectionList = (List<Section>) sectionRepository.findAll();
        return sectionList;
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
    public Section createSection(@RequestBody @Valid Section section, Errors errors) {
        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Validation failed");
        }

        Section newSection = sectionRepository.save(section);
        return newSection;
    }
}
