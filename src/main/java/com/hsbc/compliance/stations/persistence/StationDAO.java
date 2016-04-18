package com.hsbc.compliance.stations.persistence;

import com.hsbc.compliance.stations.entity.Station;

import java.util.List;

public interface StationDAO {
    public void save(Station station);
    public List<Station> search(String name);
}
