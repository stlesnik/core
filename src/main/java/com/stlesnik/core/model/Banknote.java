package com.stlesnik.core.model;

public class Banknote {
    private int denomination;

    private int amount;

    private String currency;

    public Banknote(){
        this.denomination = 0;
        this.amount = 0;
        this.currency = null;}


    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
