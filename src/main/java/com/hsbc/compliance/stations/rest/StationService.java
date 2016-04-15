package com.hsbc.compliance.stations.rest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/station")
public class StationService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name")
    public String getString() {
        return "Hello World";
    }

}
