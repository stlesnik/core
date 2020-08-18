package com.stlesnik.core.dao;

import com.stlesnik.core.model.Cassette;
import com.stlesnik.core.model.Counter;
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
        if(cassette.getType().equals(Cassette.CassetteType.In)){
            int[] values = {100, 200, 500, 1000, 2000, 5000};
            for (int item : values) {
                Counter counter = new Counter();
                counter.setValue(item);
                counter.setNumber(0);
                counter.setC_id(cassette);

                this.entityManager.merge(counter);
            }
        }
        else if(cassette.getType().equals(Cassette.CassetteType.Recycling)){
            Counter counter = new Counter();
            counter.setValue(cassette.getValue());
            counter.setNumber(0);
            counter.setC_id(cassette);

            this.entityManager.merge(counter);
        }

        logger.info("cassette successfully saved. Cassette details: " + cassette);
    }

    @Override
    public String updateCassette(Cassette cassette) {

        List<Cassette> cassetteList = entityManager.createQuery(
                "SELECT a FROM Cassettes a" + " WHERE a.id = " + cassette.getId(), Cassette.class).getResultList();
        if (cassetteList == null || cassetteList.isEmpty()) {
            return "there is nothing to update update";
        }
        else {
            Cassette oldCassette = cassetteList.get(0);
            oldCassette.setName(cassette.getName());
            oldCassette.setType(cassette.getType());
            if(cassette.getType().equals(Cassette.CassetteType.Recycling)){
                oldCassette.setValue(cassette.getValue());
            }
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
    public List<Cassette> listCassette() {
        List<Cassette> cassetteList = entityManager.createQuery(
                "SELECT a FROM Cassette a", Cassette.class).getResultList();
        return cassetteList;
    }

    @Override
    public void changeAmountOnCounter(int cassette_id, int number) {
        Counter counter = entityManager.createQuery(
                "SELECT a FROM Counters a"
                        + "WHERE a.cassette_id = " + cassette_id, Counter.class).getSingleResult();
    }

    @Override
    public int getNumOfNotes(int value) {
        logger.info("value = " + value);
         Counter counter = entityManager.createQuery("SELECT c FROM Counter c " +
                 "WHERE c.cassette_id = (SELECT id FROM Cassette i WHERE i.value = "+value+" )", Counter.class).getSingleResult();
         return counter.getNumber();
    }

    @Override
    public void withdrawCounters(int[][] notes) {
        for(int i = 0; i<6; i++){
            Counter counter = entityManager.createQuery("SELECT c " +
                    "FROM Counter c " +
                    "where c.cassette_id = (SELECT id " +
                    "FROM Cassette i " +
                    "WHERE i.value = " + notes[i][0] +
                    " ) ", Counter.class).getSingleResult();
            counter.setNumber(counter.getNumber() - notes[i][1]);
            entityManager.merge(counter);
        }
    }
}
