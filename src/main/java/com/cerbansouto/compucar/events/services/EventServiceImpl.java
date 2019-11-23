package com.cerbansouto.compucar.events.services;

import com.cerbansouto.compucar.events.api.EntityNotFoundException;
import com.cerbansouto.compucar.events.api.EventService;
import com.cerbansouto.compucar.events.domain.Event;
import com.cerbansouto.compucar.events.domain.EventKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repository;

    @Override
    public List<Event> list() {
        return repository.findAll();
    }

    @Override
    public List<Event> listForService(String serviceCode) {
        return repository.findByKeyServiceCode(serviceCode);
    }

    @Override
    public Event fetch(EventKey key) throws EntityNotFoundException {
        Optional<Event> event = repository.findById(key);
        if (event.isPresent()) {
            return event.get();
        }

        throw new EntityNotFoundException(String.format("No event by key %s", key.toString()));
    }

    @Override
    public void delete(EventKey key) {
        repository.deleteById(key);
    }

    @Override
    public Event create(Event event) {
        Optional<Event> dup = repository.findById(event.getKey());
        if (dup.isPresent()) {
            Event eventToUpdate = dup.get();
            eventToUpdate.setPayload(event.getPayload());
            return repository.save(eventToUpdate);
        }

        return repository.save(event);
    }

    @Override
    public Event update(Event event) throws EntityNotFoundException {
        // TODO: missing validations
        Event eventToUpdate = fetch(event.getKey());
        eventToUpdate.setPayload(event.getPayload());
        return repository.save(eventToUpdate);
    }
}
