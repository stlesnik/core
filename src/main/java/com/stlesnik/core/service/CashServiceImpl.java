package com.stlesnik.core.service;

import com.stlesnik.core.dao.CassetteDao;
import com.stlesnik.core.model.Banknote;
import com.stlesnik.core.model.Withdraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CashServiceImpl implements CashService {

    @Autowired
    private CassetteDao cassetteDao;

    public CashServiceImpl(){
    }

    public CashServiceImpl(CassetteDao cassetteDao) {
        this.cassetteDao = cassetteDao;
    }

    @Override
    @Transactional
    public Withdraw withdrawMoney(int amount) throws Exception {
        Withdraw withdraw = new Withdraw();

        if(amount % 100 != 0){
                throw new Exception("You can only withdraw a multiple of 100!");
        }
        else{
            int temp = amount;

            List<Banknote> banknotes = new ArrayList<Banknote>();
            int[] notes = {5000, 2000, 1000, 500, 200, 100};
            for (int note : notes) {

                Banknote b = new Banknote();
                b.setDenomination(note);
                b.setAmount(temp / note);
                b.setCurrency("RUB");

                int numOfNotes = cassetteDao.getCurrentCounter(b.getDenomination());
                if (numOfNotes >= b.getAmount()) {
                    temp = temp - b.getDenomination() * b.getAmount();
                }
                else {
                    temp = temp - b.getDenomination() * numOfNotes;
                    b.setAmount(numOfNotes);
                }

                banknotes.add(b);
            }
            if(temp!=0){
                throw new Exception("it is impossible to issue the amount");
            }
            else{
                cassetteDao.withdrawMoney(banknotes);
                withdraw.setBanknotes(banknotes);
            }
        }
        return withdraw;
    }

    @Override
    @Transactional
    public String depositMoney(int[] notes) {
        cassetteDao.depositMoney(notes);
        return "Success";
    }
}
