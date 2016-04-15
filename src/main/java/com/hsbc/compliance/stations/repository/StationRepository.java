package com.hsbc.compliance.stations.repository;

import com.hsbc.compliance.stations.entity.Station;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ravi on 15/04/2016.
 */
public interface StationRepository extends CrudRepository<Station, Long> {
    List<Station> findByStationLike(String name);
}
