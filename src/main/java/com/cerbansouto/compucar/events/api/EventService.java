package com.cerbansouto.compucar.events.api;

import com.cerbansouto.compucar.events.domain.Event;
import com.cerbansouto.compucar.events.domain.EventKey;

import java.util.List;

public interface EventService {
    List<Event> list();
    List<Event> listForService(String serviceCode);
    Event fetch(EventKey key) throws EntityNotFoundException;
    Event create(Event event) throws InvalidEventException;
    Event update(Event event) throws EntityNotFoundException;
    void delete(EventKey key);
}
