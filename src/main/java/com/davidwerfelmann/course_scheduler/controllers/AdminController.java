package com.davidwerfelmann.course_scheduler.controllers;

import com.davidwerfelmann.course_scheduler.utilities.WebScraper;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final WebScraper webScraper;

    public AdminController(WebScraper webScraper) {
        this.webScraper = webScraper;
    }

    @PostMapping("/import-catalog")
    public ResponseEntity<String> importCatalog(@RequestParam String url) {
        webScraper.importCatalog(url);
        return ResponseEntity.ok("Catalog import triggered");
    }
}
