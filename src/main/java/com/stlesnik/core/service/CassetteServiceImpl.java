package com.stlesnik.core.service;

import com.stlesnik.core.dao.AtmDao;
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
    private AtmDao atmDao;

    public CassetteServiceImpl(){
    }

    public CassetteServiceImpl(AtmDao atmDao) {
        this.atmDao = atmDao;
    }


    @Override
    @Transactional
    public void addCassette(Cassette cassette) {
        atmDao.addCassette(cassette);
    }

    @Override
    @Transactional
    public String updateCassette(Cassette cassette) {
        return atmDao.updateCassette(cassette);
    }

    @Override
    @Transactional
    public void removeCassette(int id) {
        atmDao.removeCassette(id);
    }

    @Override
    @Transactional
    public Cassette getCassetteById(int id) {
        return atmDao.getCassetteById(id);
    }

    @Override
    @Transactional
    public List<Cassette> listCassette() {
        return atmDao.listCassette();
    }

}
