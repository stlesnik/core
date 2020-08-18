package com.stlesnik.core.service;

import com.stlesnik.core.model.Cassette;
import com.stlesnik.core.model.Counter;

import java.util.List;

public interface CoreService {
    public void addCassette(Cassette cassette);

    public String updateCassette(Cassette cassette);

    public void removeCassette(int id);

    public Cassette getCassetteById(int id);

    public List<Cassette> listCassette();

    public int getNumOfNotes(int value);

    public void withdrawCounters(int[][] notes);
}
