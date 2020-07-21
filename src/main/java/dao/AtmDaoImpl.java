package dao;

import model.Atm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AtmDaoImpl implements AtmDao {
    private static final Logger logger= LoggerFactory.getLogger(AtmDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addAtm(Atm atm) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(atm);
        logger.info("Atm successfully added. Atm details: "+ atm);
    }

    @Override
    public void updateAtm(Atm atm) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(atm);
        logger.info("Atm successfully updated. Atm details: " + atm);
    }

    @Override
    public void removeAtm(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Atm atm = session.load(Atm.class, id);
        if(atm != null){
            session.delete(atm);
        }
        logger.info("Atm successfully deleted. Atm details: " + atm);
    }

    @Override
    public Atm getAtmById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Atm atm = session.load(Atm.class, id);
        logger.info("Atm successfully loaded. Atm details: " + atm);
        return atm;
    }

    @Override
    public List<Atm> listAtm() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Atm> atmList= session.createQuery("from Atm").list();
        return atmList;
    }
}
