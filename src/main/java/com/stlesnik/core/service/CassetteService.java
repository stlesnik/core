package com.stlesnik.core.service;

import com.stlesnik.core.model.Cassette;

import java.util.List;

public interface CassetteService {
    public void addCassette(Cassette cassette);

    public String updateCassette(Cassette cassette);

    public void removeCassette(int id);

    public Cassette getCassetteById(int id);

    public List<Cassette> listCassette();

}
