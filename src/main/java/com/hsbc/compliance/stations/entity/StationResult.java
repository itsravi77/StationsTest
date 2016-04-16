package com.hsbc.compliance.stations.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 16/04/2016.
 */
public class StationResult {

    private List<Station> stations;
    public StationResult() {
        stations = new ArrayList<>();
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
