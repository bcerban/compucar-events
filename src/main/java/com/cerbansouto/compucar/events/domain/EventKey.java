package com.cerbansouto.compucar.events.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Objects;

@Data
@PrimaryKeyClass
public class EventKey implements Serializable {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private String serviceCode;

    @PrimaryKeyColumn
    private String name;

    public EventKey(String serviceCode, String name) {
        this.serviceCode = serviceCode;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("(serviceCode: %s, name: %s)", serviceCode, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventKey eventKey = (EventKey) o;
        return Objects.equals(serviceCode, eventKey.serviceCode) &&
                Objects.equals(name, eventKey.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceCode, name);
    }
}
