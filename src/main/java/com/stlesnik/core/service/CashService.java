package com.stlesnik.core.service;

import com.stlesnik.core.model.Withdraw;

public interface CashService {
    public Withdraw withdrawMoney(int amount) throws Exception;

    public String depositMoney(int[] notes);
}
