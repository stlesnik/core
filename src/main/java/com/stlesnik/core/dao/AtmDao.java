package com.stlesnik.core.dao;


import com.stlesnik.core.model.Banknote;
import com.stlesnik.core.model.Cassette;
import com.stlesnik.core.model.Counter;

import java.util.List;

public interface AtmDao {
    public void addCassette(Cassette cassette);

    public String updateCassette(Cassette cassette);

    public void removeCassette(int id);

    public Cassette getCassetteById(int id);

    public List<Cassette> listCassette();

    public int getCurrentCounter(int value);

    public void withdrawMoney(List<Banknote> banknotes);

    public void depositMoney(List<Banknote> banknotes);
}
