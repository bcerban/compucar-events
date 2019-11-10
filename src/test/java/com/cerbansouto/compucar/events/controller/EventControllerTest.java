package com.cerbansouto.compucar.events.controller;

import com.cerbansouto.compucar.events.api.EntityNotFoundException;
import com.cerbansouto.compucar.events.api.EventService;
import com.cerbansouto.compucar.events.api.InvalidEventException;
import com.cerbansouto.compucar.events.domain.Event;
import com.cerbansouto.compucar.events.domain.EventKey;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class EventControllerTest {
    @Mock
    private EventService service;

    @InjectMocks
    private EventController controller;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getEvents() {
        when(service.list()).thenReturn(new ArrayList<>());
        controller.getEvents();

        verify(service, times(1)).list();
    }

    @Test
    public void getEventsForService() {
        String serviceCode = "testCode";
        when(service.listForService(serviceCode)).thenReturn(new ArrayList<>());
        controller.getEventsForService(serviceCode);

        verify(service, times(1)).listForService(serviceCode);
    }

    @Test
    public void fetchEvent() throws EntityNotFoundException {
        String serviceCode = "testCode";
        String eventName = "testName";
        when(service.fetch(any(EventKey.class))).thenReturn(new Event());
        controller.fetchEvent(serviceCode, eventName);

        verify(service, times(1)).fetch(any(EventKey.class));
    }

    @Test
    public void createEvent() throws InvalidEventException {
        Event event = new Event();
        when(service.create(event)).thenReturn(event);
        controller.createEvent(event);

        verify(service, times(1)).create(event);
    }

    @Test
    public void updateEvent() throws EntityNotFoundException {
        Event event = new Event();
        when(service.update(event)).thenReturn(event);
        controller.updateEvent(event);

        verify(service, times(1)).update(event);
    }

    @Test
    public void deleteEvent() {
        String serviceCode = "testCode";
        String eventName = "testName";
        controller.deleteEvent(serviceCode, eventName);

        verify(service, times(1)).delete(any(EventKey.class));
    }
}
