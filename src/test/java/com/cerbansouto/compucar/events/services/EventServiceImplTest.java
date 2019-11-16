package com.cerbansouto.compucar.events.services;

import com.cerbansouto.compucar.events.api.EntityNotFoundException;
import com.cerbansouto.compucar.events.api.InvalidEventException;
import com.cerbansouto.compucar.events.domain.Event;
import com.cerbansouto.compucar.events.domain.EventKey;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EventServiceImplTest {
    @Mock
    private EventRepository repository;

    @InjectMocks
    private EventServiceImpl service;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void list() {
        when(repository.findAll()).thenReturn(new ArrayList<>());
        service.list();

        verify(repository, times(1)).findAll();
    }

    @Test
    public void listForService() {
        String serviceCode = "testCode";
        when(repository.findByKeyServiceCode(serviceCode)).thenReturn(new ArrayList<>());
        service.listForService(serviceCode);

        verify(repository, times(1)).findByKeyServiceCode(serviceCode);
    }

    @Test(expected = EntityNotFoundException.class)
    public void fetchNotFound() throws EntityNotFoundException {
        EventKey key = new EventKey("testCode", "testName");
        when(repository.findById(key)).thenReturn(Optional.empty());
        service.fetch(key);

        verify(repository, times(1)).findById(key);
    }

    @Test
    public void fetch() throws EntityNotFoundException {
        EventKey key = new EventKey("testCode", "testName");
        when(repository.findById(key)).thenReturn(Optional.of(new Event(key, "")));
        service.fetch(key);

        verify(repository, times(1)).findById(key);
    }

    @Test
    public void delete() {
        EventKey key = new EventKey("testCode", "testName");
        service.delete(key);

        verify(repository, times(1)).deleteById(key);
    }

    @Test
    public void createDuplicate() {
        Event event = new Event(new EventKey("testCode", "testName"), "");
        when(repository.findById(event.getKey())).thenReturn(Optional.of(event));
        service.create(event);

        verify(repository, times(1)).findById(event.getKey());
        verify(repository, times(1)).save(event);
    }

    @Test
    public void create() {
        Event event = new Event(new EventKey("testCode", "testName"), "");
        when(repository.findById(event.getKey())).thenReturn(Optional.empty());
        service.create(event);

        verify(repository, times(1)).findById(event.getKey());
        verify(repository, times(1)).save(event);
    }

    @Test
    public void update() throws EntityNotFoundException {
        Event event = new Event(new EventKey("testCode", "testName"), "");
        when(repository.findById(event.getKey())).thenReturn(Optional.of(event));
        service.update(event);

        verify(repository, times(1)).findById(event.getKey());
        verify(repository, times(1)).save(event);
    }
}
