package com.hsbc.compliance.stations.persistence;

import com.hsbc.compliance.stations.entity.Station;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by ravi on 16/04/2016.
 */
public class HibernateBasedStationDAO implements StationDAO {
    private final SessionFactory sessionFactory;

    public HibernateBasedStationDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Station station) {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        session.persist(station);
        transaction.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Station> search(String name) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Station.class);
        criteria.add(Restrictions.ilike("name", "%"+name.toLowerCase()+"%"));

        return criteria.list();
    }
}
