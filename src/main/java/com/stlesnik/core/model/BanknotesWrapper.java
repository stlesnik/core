package com.stlesnik.core.model;

import java.util.List;

public class BanknotesWrapper {
    private List<Banknote> banknotes;

    public BanknotesWrapper(){
        this.banknotes = null;}

    public List<Banknote> getBanknotes() {
        return banknotes;
    }

    public void setBanknotes(List<Banknote> banknotes) {
        this.banknotes = banknotes;
    }
}
