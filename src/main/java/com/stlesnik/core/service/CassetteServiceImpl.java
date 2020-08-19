package com.stlesnik.core.service;

import com.stlesnik.core.dao.CassetteDao;
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
    private CassetteDao cassetteDao;

    public CassetteServiceImpl(){
    }

    public CassetteServiceImpl(CassetteDao cassetteDao) {
        this.cassetteDao = cassetteDao;
    }


    @Override
    @Transactional
    public void addCassette(Cassette cassette) {
        cassetteDao.addCassette(cassette);
    }

    @Override
    @Transactional
    public String updateCassette(Cassette cassette) {
        return cassetteDao.updateCassette(cassette);
    }

    @Override
    @Transactional
    public void removeCassette(int id) {
        cassetteDao.removeCassette(id);
    }

    @Override
    @Transactional
    public Cassette getCassetteById(int id) {
        return cassetteDao.getCassetteById(id);
    }

    @Override
    @Transactional
    public List<Cassette> listCassette() {
        return cassetteDao.listCassette();
    }

}
