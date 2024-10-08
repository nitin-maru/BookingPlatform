package org.example.bookingplatform.controller;

import org.example.bookingplatform.Entity.Screen;
import org.example.bookingplatform.service.IScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screens")
public class ScreenController {

    @Autowired
    private IScreenService screenService;

    @GetMapping
    public List<Screen> getAllScreens() {
        return screenService.getAllScreens();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Screen> getScreenById(@PathVariable Long id) {
        return screenService.getScreenById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Screen createScreen(@RequestBody Screen screen) {
        return screenService.createScreen(screen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Screen> updateScreen(@PathVariable Long id, @RequestBody Screen screen) {
        return screenService.updateScreen(id, screen) != null ?
                ResponseEntity.ok(screen) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScreen(@PathVariable Long id) {
        screenService.deleteScreen(id);
        return ResponseEntity.noContent().build();
    }
}
