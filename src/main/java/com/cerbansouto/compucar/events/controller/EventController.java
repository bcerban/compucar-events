package com.cerbansouto.compucar.events.controller;

import com.cerbansouto.compucar.events.api.EntityNotFoundException;
import com.cerbansouto.compucar.events.api.EventService;
import com.cerbansouto.compucar.events.api.InvalidEventException;
import com.cerbansouto.compucar.events.domain.Event;
import com.cerbansouto.compucar.events.domain.EventKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Event> getEventsForService(@PathVariable String serviceCode) {
        return service.listForService(serviceCode);
    }

    @GetMapping("/{serviceCode}/name/{eventName}")
    public Event fetchEvent(@PathVariable String serviceCode, @PathVariable String eventName) throws EntityNotFoundException {
        return service.fetch(new EventKey(serviceCode, eventName));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Event createEvent(@RequestBody Event event) throws InvalidEventException {
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
