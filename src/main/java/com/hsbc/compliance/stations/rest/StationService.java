package com.hsbc.compliance.stations.rest;


import com.hsbc.compliance.stations.entity.Station;
import com.hsbc.compliance.stations.entity.StationResult;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@Path("/station")
public class StationService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search/{name}")
    public StationResult getString(@PathParam("name") String name) {
        StationResult result = new StationResult();
        result.setStations(Arrays.asList(new Station(name), new Station("Sukheja")));

        return result;
    }

}
