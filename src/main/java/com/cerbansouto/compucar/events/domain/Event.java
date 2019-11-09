package com.cerbansouto.compucar.events.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table
public class Event {

    @PrimaryKey
    private EventKey key;

    private String payload;

    public Event() { }

    public Event(EventKey key, String payload) {
        this.key = key;
        this.payload = payload;
    }
}
