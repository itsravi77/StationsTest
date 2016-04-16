package com.hsbc.compliance.stations.rest;


import com.hsbc.compliance.stations.entity.Station;
import com.hsbc.compliance.stations.entity.StationResult;
import org.hibernate.*;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/station")
public class StationService extends Application {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search/{name}")
    public StationResult getStation(@PathParam("name") String name) {
        Session session = new Configuration()
                .configure().buildSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        session.persist(new Station("DARTFORD"));
        session.persist(new Station("DARTMOUTH"));
        session.persist(new Station("TOWER HILL"));
        session.persist(new Station("DERBY"));
        session.persist(new Station("LIVERPOOL"));
        session.persist(new Station("LIVERPOOL LIME STREET"));
        session.persist(new Station("PADDINGTON"));
        session.persist(new Station("EUSTON"));
        session.persist(new Station("LONDON BRIDGE"));
        session.persist(new Station("VICTORIA"));
        transaction.commit();

        System.out.println("Inserted Ravi Station");
        Query query = session.createQuery("from Station");
        List list = query.list();
        System.out.println("retrieved: " + list);
        Criteria criteria = session.createCriteria(Station.class);
        criteria.add(Restrictions.ilike("name", "%"+name.toLowerCase()+"%"));
        List<Station> list1 = criteria.list();

        System.out.println("Retrieved: " + list1);


        StationResult result = new StationResult();
        result.setStations(list1);

        return result;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/searchAll")
    public StationResult getAll() {

        Session session = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory().openSession();

        Transaction t=session.beginTransaction();
        session.persist(new Station("DARTFORD"));
        session.persist(new Station("DARTMOUTH"));
        session.persist(new Station("TOWER HILL"));
        session.persist(new Station("DERBY"));
        session.persist(new Station("LIVERPOOL"));
        session.persist(new Station("LIVERPOOL LIME STREET"));
        session.persist(new Station("PADDINGTON"));
        session.persist(new Station("EUSTON"));
        session.persist(new Station("LONDON BRIDGE"));
        session.persist(new Station("VICTORIA"));
        t.commit();

        System.out.println("Inserted Ravi Station");
        Query query = session.createQuery("from Station");
        List<Station> list = query.list();
        StationResult result = new StationResult();
        result.setStations(list);

        return result;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/health")
    public String getHealth() {
        return "Service is up.";
    }
}
