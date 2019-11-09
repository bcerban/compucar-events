package com.cerbansouto.compucar.events.services;

import com.cerbansouto.compucar.events.api.EntityNotFoundException;
import com.cerbansouto.compucar.events.api.EventService;
import com.cerbansouto.compucar.events.domain.Event;
import com.cerbansouto.compucar.events.domain.EventKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

    }

    @Override
    public Event create(Event event) {
        return null;
    }

    @Override
    public Event update(Event event) {
        return null;
    }

    @PostConstruct
    public void init() {
        repository.save(new Event(new EventKey("SRV010", "ENGINE_SENSOR"), ""));
        repository.save(new Event(new EventKey("SRV010", "O2_SENSOR"), ""));
        repository.save(new Event(new EventKey("SRV010", "FUEL_PRESSURE_SENSOR"), ""));
        repository.save(new Event(new EventKey("SRV010", "AIR_FLOW_SENSOR"), ""));
        repository.save(new Event(new EventKey("SRV017", "CRANKSHAFT_SENSOR"), ""));
        repository.save(new Event(new EventKey("SRV017", "O2_SENSOR"), ""));
        repository.save(new Event(new EventKey("SRV017", "VOLTAGE_SENSOR"), ""));
        repository.save(new Event(new EventKey("SRV017", "NOX_SENSOR"), ""));
    }
}
