package com.cerbansouto.compucar.events.services;

import com.cerbansouto.compucar.events.domain.Event;
import com.cerbansouto.compucar.events.domain.EventKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface EventRepository extends CassandraRepository<Event, EventKey> {
    List<Event> findByKeyServiceCode(String serviceCode);
}
