package dao;

import model.Atm;

import java.util.List;

public interface AtmDao {

    public void addAtm(Atm atm);

    public void updateAtm(Atm atm);

    public void removeAtm(int id);

    public Atm getAtmById(int id);

    public List<Atm> listAtm();
}
