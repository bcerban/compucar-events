package com.cerbansouto.compucar.events.controller;

import com.cerbansouto.compucar.events.api.EntityNotFoundException;
import com.cerbansouto.compucar.events.api.EventService;
import com.cerbansouto.compucar.events.domain.Event;
import com.cerbansouto.compucar.events.domain.EventKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
