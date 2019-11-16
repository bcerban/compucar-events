package com.cerbansouto.compucar.events.controller;

import com.cerbansouto.compucar.events.api.EntityNotFoundException;
import com.cerbansouto.compucar.events.api.EventService;
import com.cerbansouto.compucar.events.api.InvalidEventException;
import com.cerbansouto.compucar.events.domain.Event;
import com.cerbansouto.compucar.events.domain.EventKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService service;

    @GetMapping
    public List<Event> getEvents() {
        return service.list();
    }

    @GetMapping("/{serviceCode}")
    public List<String> getEventsForService(@PathVariable String serviceCode) {
        List<Event> events = service.listForService(serviceCode);
        return events.stream().map(e -> e.getKey().getName()).collect(Collectors.toList());
    }

    @GetMapping("/{serviceCode}/name/{eventName}")
    public Event fetchEvent(@PathVariable String serviceCode, @PathVariable String eventName) throws EntityNotFoundException {
        return service.fetch(new EventKey(serviceCode, eventName));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Event createEvent(@RequestBody Event event) {
        log.info("Invoked createEvent with {}", event);
        return service.create(event);
    }

    @PutMapping
    public Event updateEvent(@RequestBody Event event) throws EntityNotFoundException {
        return service.update(event);
    }

    @DeleteMapping("/{serviceCode}/name/{eventName}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable String serviceCode, @PathVariable String eventName) {
        service.delete(new EventKey(serviceCode, eventName));
    }
}
