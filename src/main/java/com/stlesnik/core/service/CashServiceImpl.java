package com.stlesnik.core.service;

import com.stlesnik.core.dao.CassetteDao;
import com.stlesnik.core.model.Withdraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Withdraw withdrawMoney(int amount){
        Withdraw withdraw = new Withdraw();

        if(amount % 100 != 0){
             withdraw.setErrorMessage("You can only withdraw a multiple of 100!");
        }
        else{
            int temp = amount;

            int[][] notes = {{5000, 0}, {2000, 0}, {1000, 0}, {500, 0}, {200, 0}, {100, 0}};
            for(int i = 0; i < notes.length; i++){
                notes[i][1] = temp / notes[i][0];
                int numOfNotes = cassetteDao.getCurrentCounter(notes[i][0]);
                if(numOfNotes>=notes[i][1]){temp = temp - notes[i][0] * notes[i][1];}
                else{
                    temp = temp - notes[i][0] * numOfNotes;
                    notes[i][1] = numOfNotes;
                }
            }
            if(temp!=0){withdraw.setErrorMessage("it is impossible to issue the amount");}
            else{
                cassetteDao.withdrawMoney(notes);
                withdraw.setNotes(notes);
                withdraw.setWithdrawOutput("Success " +System.lineSeparator() +
                        "Сумма снятия - " + amount +System.lineSeparator() +
                        " Кол-во купюр 5000:" + notes[0][1] + System.lineSeparator() +
                        " Кол-во купюр 2000:" + notes[1][1] +System.lineSeparator()+
                        " Кол-во купюр 1000:" + notes[2][1] +System.lineSeparator() +
                        " Кол-во купюр  500:" + notes[3][1] +System.lineSeparator() +
                        " Кол-во купюр  200:" + notes[4][1] +System.lineSeparator() +
                        " Кол-во купюр  100:" + notes[5][1]);
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
