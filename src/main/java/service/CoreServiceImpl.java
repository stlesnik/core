package service;

import dao.CoreDao;
import model.Cassette;
import model.Counter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoreServiceImpl implements CoreService{
    private CoreDao coreDao;


    private CoreServiceImpl(){
    }

    private CoreServiceImpl(CoreDao coreDao) {
        this.coreDao = coreDao;
    }


    @Override
    @Transactional
    public void addCassette(Cassette cassette) {
        coreDao.addCassette(cassette);
    }

    @Override
    @Transactional
    public void updateCassette(Cassette cassette) {
        coreDao.updateCassette(cassette);
    }

    @Override
    @Transactional
    public void removeCassette(int id) {
        coreDao.removeCassette(id);
    }

    @Override
    @Transactional
    public Cassette getCassetteById(int id) {
        return coreDao.getCassetteById(id);
    }

    @Override
    @Transactional
    public Counter getCounterById(int id) {
        return coreDao.getCounterById(id);
    }

    @Override
    @Transactional
    public List<Cassette> listCassette() {
        return coreDao.listCassette();
    }
}