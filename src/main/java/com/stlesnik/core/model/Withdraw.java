package com.stlesnik.core.model;

public class Withdraw {
    private int[][] notes;

    private String errorMessage;

    private String withdrawOutput;

    public Withdraw(){
        this.notes = null;
        this.errorMessage = null;
        this.withdrawOutput = null;}

    public int[][] getNotes() {
        return notes;
    }

    public void setNotes(int[][] notes) {
        this.notes = notes;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getWithdrawOutput() {
        return withdrawOutput;
    }

    public void setWithdrawOutput(String withdrawOutput) {
        this.withdrawOutput = withdrawOutput;
    }
}
