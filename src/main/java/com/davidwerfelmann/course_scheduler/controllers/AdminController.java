package com.davidwerfelmann.course_scheduler.controllers;

import com.davidwerfelmann.course_scheduler.data.WebScraper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final WebScraper webScraper;

    public AdminController(WebScraper webScraper) {
        this.webScraper = webScraper;
    }

    @PostMapping("/import-catalog")
    public ResponseEntity<String> importCatalog() {
        webScraper.importCatalog();
        return ResponseEntity.ok("Catalog import triggered");
    }
}
