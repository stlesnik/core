package com.stlesnik.core.dao;


import com.stlesnik.core.model.Cassette;

import java.util.List;

public interface CassetteDao {
    public void addCassette(Cassette cassette);

    public String updateCassette(Cassette cassette);

    public void removeCassette(int id);

    public Cassette getCassetteById(int id);

    public List<Cassette> listCassette();

    public int getCurrentCounter(int value);

    public void withdrawMoney(int[][] notes);

    public void depositMoney(int[] notes);
}
