package org.example.backend.Service;

import org.example.backend.Entity.Events;
import org.example.backend.Repository.Event_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private Event_Repo repository;

    @Autowired
    public void Repository(Event_Repo repository) {
        this.repository = repository;
    }

    public ResponseEntity<List<Events>> getAllEvents() {
        List<Events> events = repository.findAll();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
    public ResponseEntity<Events> getEventById(int id) {
        Optional<Events> eventOptional = repository.findById(id);
        return eventOptional.map(events -> new ResponseEntity<>(events, HttpStatus.OK)).orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    public ResponseEntity<Events> createEvent(Events event) {
        Events newEvent = repository.save(event);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }
    public ResponseEntity<Events> updateEvent(int id, Events updateEvent) {
        Optional<Events> eventOptional = repository.findById(id);
        if (eventOptional.isPresent()) {
            Events eventToUpdate = eventOptional.get();
            eventToUpdate.setTitle(updateEvent.getTitle());
            eventToUpdate.setDescription(updateEvent.getDescription());
            Events updatedEvent = repository.save(eventToUpdate);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Void> deleteEvent(int id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
