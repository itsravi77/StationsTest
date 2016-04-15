package com.hsbc.compliance.stations.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Station {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    //Required protected for Spring JPA
    protected Station() {}

    public Station(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
