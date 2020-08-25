package com.stlesnik.core.service;

import com.stlesnik.core.model.BanknotesWrapper;

public interface CashService {
    public BanknotesWrapper withdrawMoney(int amount) throws Exception;

    public BanknotesWrapper withdrawMoneyWithExchange(int amount) throws Exception;

    public String depositMoney(BanknotesWrapper banknotesWrapper);
}
