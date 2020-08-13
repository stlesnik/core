package com.stlesnik.core.service;

import com.stlesnik.core.dao.CoreDao;
import com.stlesnik.core.model.Cassette;
import com.stlesnik.core.model.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoreServiceImpl implements CoreService{
    Logger logger = LoggerFactory.getLogger(CoreServiceImpl.class);

    @Autowired
    private CoreDao coreDao;

    public CoreServiceImpl(){
    }

    public CoreServiceImpl(CoreDao coreDao) {
        this.coreDao = coreDao;
    }


    @Override
    @Transactional
    public void addCassette(Cassette cassette) {
        coreDao.addCassette(cassette);
    }

    @Override
    @Transactional
    public String updateCassette(Cassette cassette) {
        return coreDao.updateCassette(cassette);
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
