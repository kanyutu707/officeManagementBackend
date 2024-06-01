package org.example.backend.Controller;

import org.example.backend.Service.EventService;
import org.example.backend.Entity.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600, allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/smartEmployer/events")
public class EventController {
    private EventService service;

    @Autowired
    public void Service(EventService service) {
        this.service = service;
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Events>> getAllEvent() {
        return service.getAllEvents();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Events> getEventById(@PathVariable int id) {
        return service.getEventById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Events> createEvent(@RequestBody Events event) {
        return service.createEvent(event);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Events> updateEvent(@PathVariable int id, @RequestBody Events event) {
        return service.updateEvent(id, event);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable int id) {
        service.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

}
