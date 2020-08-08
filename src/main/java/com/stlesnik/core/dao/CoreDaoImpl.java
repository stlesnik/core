package com.stlesnik.core.dao;

import com.stlesnik.core.model.Cassette;
import com.stlesnik.core.model.Counter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoreDaoImpl implements CoreDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    Logger logger = LoggerFactory.getLogger(CoreDaoImpl.class);


    @Override
    public void addCassette(Cassette cassette) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(cassette);
    }

    @Override
    public void updateCassette(Cassette cassette) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(cassette);
    }

    @Override
    public void removeCassette(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Cassette cassette = session.load(Cassette.class, id);
        if (cassette != null) {
            session.delete(cassette);
        }
    }

    @Override
    public Cassette getCassetteById(int id) {
        logger.info("hello from daoImpl");
        Session session = this.sessionFactory.getCurrentSession();
        logger.info("get session");
        Cassette cassette = session.load(Cassette.class, id);
        return cassette;
    }

    @Override
    public Counter getCounterById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Counter counter = session.load(Counter.class, id);
        return counter;
    }

    @Override
    public List<Cassette> listCassette() {
        Session session = this.sessionFactory.getCurrentSession();
        logger.info("session opened");
        System.out.println(session);
        List<Cassette>  cassetteList = session.createQuery("from cassettes").list();
        return cassetteList;
    }
}