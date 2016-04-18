package com.hsbc.compliance.stations.rest;


import com.hsbc.compliance.stations.entity.Station;
import com.hsbc.compliance.stations.entity.StationResult;
import com.hsbc.compliance.stations.persistence.HibernateBasedStationDAO;
import com.hsbc.compliance.stations.persistence.StationDAO;
import org.apache.commons.io.FileUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Path("/station")
public class StationService extends Application {

    private static StationDAO stationDAO;
    static {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        stationDAO = (StationDAO) context.getBean("stationHibernateDAO");

        //Loads the test data. TODO: In practice this wouldn't be there
        try {
            List<String> lines = FileUtils.readLines(new File("src/test/resources/stations.txt"));
            for (String line : lines) {
                stationDAO.save(new Station(line));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search/{name}")
    public StationResult getStation(@PathParam("name") String name) {
        StationResult result = new StationResult();
        result.setStations(stationDAO.search(name));
        return result;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/searchAll")
    public StationResult getAll() {
        StationResult result = new StationResult();
        result.setStations(stationDAO.search(""));
        return result;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/health")
    public String getHealth() {
        return "Service is up.";
    }
}
