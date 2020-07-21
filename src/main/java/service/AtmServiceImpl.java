package service;

import dao.AtmDao;
import model.Atm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AtmServiceImpl implements AtmService {
    private AtmDao atmDao;

    public void setAtmDao(AtmDao atmDao) {
        this.atmDao = atmDao;
    }

    @Override
    @Transactional
    public void addAtm(Atm atm) {
        atmDao.addAtm(atm);
    }

    @Override
    @Transactional
    public void updateAtm(Atm atm) {
        atmDao.updateAtm(atm);
    }

    @Override
    @Transactional
    public void removeAtm(int id) {
        atmDao.removeAtm(id);
    }

    @Override
    @Transactional
    public Atm getAtmById(int id) {
        return atmDao.getAtmById(id);
    }

    @Override
    @Transactional
    public List<Atm> listAtm() {
        return atmDao.listAtm();
    }
}
