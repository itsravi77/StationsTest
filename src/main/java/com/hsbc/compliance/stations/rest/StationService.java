package com.hsbc.compliance.stations.rest;


import com.hsbc.compliance.stations.entity.Station;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/station")
public class StationService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search/{name}")
    public Station getString(@PathParam("name") String name) {
        return new Station();
        //return "Hello World";
    }

}
