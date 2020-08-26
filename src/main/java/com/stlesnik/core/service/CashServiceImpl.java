package com.stlesnik.core.service;

import com.stlesnik.core.dao.AtmDao;
import com.stlesnik.core.model.Banknote;
import com.stlesnik.core.model.BanknotesWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CashServiceImpl implements CashService {

    @Autowired
    private AtmDao atmDao;

    public CashServiceImpl(){
    }

    public CashServiceImpl(AtmDao atmDao) {
        this.atmDao = atmDao;
    }

    @Override
    @Transactional
    public BanknotesWrapper withdrawMoney(int amount) throws Exception {
        BanknotesWrapper banknotesWrapper = new BanknotesWrapper();

        if(amount % 100 != 0){
                throw new Exception("You can only BanknotesWrapper a multiple of 100!");
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

                int numOfNotes = atmDao.getCurrentCounter(b.getDenomination());
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
                atmDao.withdrawMoney(banknotes);
                banknotesWrapper.setBanknotes(banknotes);
            }
        }
        return banknotesWrapper;
    }

    @Override
    @Transactional
    public BanknotesWrapper withdrawMoneyWithExchange(int amount) throws Exception {

        BanknotesWrapper banknotesWrapper = new BanknotesWrapper();

        if(amount % 100 != 0){
            throw new Exception("You can only BanknotesWrapper a multiple of 100!");
        }
        else{
            boolean flag = false;
            int temp = amount;
            List<Banknote> banknotes = new ArrayList<Banknote>();
            int[] notes = {5000, 2000, 1000, 500, 200, 100};
            for (int note : notes) {

                Banknote b = new Banknote();
                b.setDenomination(note);
                b.setAmount(temp / note);
                b.setCurrency("RUB");

                int numOfNotes = atmDao.getCurrentCounter(b.getDenomination());
                if (numOfNotes >= b.getAmount()) {
                    temp = temp - b.getDenomination() * b.getAmount();
                }
                else {
                    temp = temp - b.getDenomination() * numOfNotes;
                    b.setAmount(numOfNotes);
                }

                if(b.getAmount() != 0 && flag == false && b.getDenomination() != 100){
                    flag = true;
                    b.setAmount(b.getAmount()-1);
                    temp = temp + b.getDenomination();
                }

                banknotes.add(b);
            }

            if(temp!=0){
                banknotesWrapper = this.withdrawMoney(amount);
            }
            else{
                atmDao.withdrawMoney(banknotes);
                banknotesWrapper.setBanknotes(banknotes);
            }
        }
        return banknotesWrapper;
    }

    @Override
    @Transactional
    public String depositMoney(BanknotesWrapper banknotesWrapper) {
        atmDao.depositMoney(banknotesWrapper.getBanknotes());
        return "Success";
    }
}
