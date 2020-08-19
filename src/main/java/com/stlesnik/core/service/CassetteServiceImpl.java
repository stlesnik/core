package com.stlesnik.core.service;

import com.stlesnik.core.dao.CoreDao;
import com.stlesnik.core.model.Cassette;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CassetteServiceImpl implements CassetteService{
    Logger logger = LoggerFactory.getLogger(CassetteServiceImpl.class);

    @Autowired
    private CoreDao coreDao;

    public CassetteServiceImpl(){
    }

    public CassetteServiceImpl(CoreDao coreDao) {
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
    public List<Cassette> listCassette() {
        return coreDao.listCassette();
    }

}
