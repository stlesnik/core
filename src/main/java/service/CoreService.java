package service;

import model.Cassette;
import model.Counter;

import java.util.List;

public interface CoreService {
    public void addCassette(Cassette cassette);

    public void updateCassette(Cassette cassette);

    public void removeCassette(int id);

    public Cassette getCassetteById(int id);

    public Counter getCounterById(int id);

    public List<Cassette> listCassette();
}
