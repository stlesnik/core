package com.stlesnik.core.dao;

import com.stlesnik.core.model.Cassette;
import com.stlesnik.core.model.Counter;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CoreDaoImpl implements CoreDao {

    @PersistenceContext
    private EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(CoreDaoImpl.class);


    @Override
    public void addCassette(Cassette cassette) {
        this.entityManager.merge(cassette);

        int cassetteId = cassette.getId();
        int[] values = {50, 100, 500, 1000, 5000};
        for (int item : values) {
            Counter counter = new Counter();
            counter.setC_id(cassette);
            counter.setName(item + " counter for cassette " + cassetteId);
            counter.setValue(item);

            this.entityManager.merge(counter);
        }

        logger.info("cassette successfully saved. Cassette details: " + cassette);
    }

    @Override
    public String updateCassette(Cassette cassette) {

        List<Cassette> cassetteList = entityManager.createQuery(
                "SELECT a FROM Cassette a" + " WHERE a.id = " + cassette.getId(), Cassette.class).getResultList();
        if (cassetteList == null || cassetteList.isEmpty()) {
            return "there is nothing to update update";
        }
        else {
            Cassette oldCassette = cassetteList.get(0);
            oldCassette.setName(cassette.getName());
            oldCassette.setDescription(cassette.getDescription());
            oldCassette.setType(cassette.getType());
            entityManager.merge(oldCassette);
            return "updated";
        }
    }

    @Override
    public void removeCassette(int id) {
        Cassette cassette = entityManager.find(Cassette.class, id);
        if (cassette != null) {
            entityManager.remove(cassette);
        }
    }

    @Override
    public Cassette getCassetteById(int id) {
        Cassette cassette = entityManager.find(Cassette.class, id);
        return cassette;
    }

    @Override
    public Counter getCounterById(int id) {
        Counter counter = entityManager.find(Counter.class, id);
        return counter;
    }

    @Override
    public List<Cassette> listCassette() {
        List<Cassette> cassetteList = entityManager.createQuery(
                "SELECT a FROM Cassette a", Cassette.class).getResultList();
        return cassetteList;
    }
}