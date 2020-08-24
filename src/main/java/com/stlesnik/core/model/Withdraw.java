package com.stlesnik.core.model;

import java.util.List;

public class Withdraw {
    private List<Banknote> banknotes;

    public Withdraw(){
        this.banknotes = null;}

    public List<Banknote> getBanknotes() {
        return banknotes;
    }

    public void setBanknotes(List<Banknote> banknotes) {
        this.banknotes = banknotes;
    }
}
