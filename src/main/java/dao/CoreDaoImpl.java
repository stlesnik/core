package dao;

import model.Cassette;
import model.Counter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoreDaoImpl implements CoreDao{
    @Autowired
    private SessionFactory sessionFactory;

    private CoreDaoImpl(){
    }

    private CoreDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



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
        if(cassette != null){
            session.delete(cassette);
        }
    }

    @Override
    public Cassette getCassetteById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
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
        List<Cassette> cassetteList= session.createQuery("from CASSETTE").list();
        return cassetteList;
    }
}