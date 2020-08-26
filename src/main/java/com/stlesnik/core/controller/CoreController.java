package com.stlesnik.core.controller;

import com.stlesnik.core.model.BanknotesWrapper;
import com.stlesnik.core.model.Cassette;
import com.stlesnik.core.service.CashService;
import com.stlesnik.core.service.CassetteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoreController {
    Logger logger = LoggerFactory.getLogger(CoreController.class);

    @Autowired
    CassetteService cassetteService;

    @Autowired
    CashService cashService;

    @RequestMapping(value = "/cassettes", method = RequestMethod.GET)
    public List<Cassette> getCassetteList() {
        logger.info("trying list");
        return cassetteService.listCassette();
    }

    @RequestMapping(value = "/cassettes/cassette/{id}", method = RequestMethod.GET)
    public Cassette getCassetteById(@PathVariable int id) {
        logger.info("id = " + id);
        return cassetteService.getCassetteById(id);
    }

    //If u need to create In or  RetractReject type, you should use 0 as value
    @RequestMapping(value = "/cassettes/cassette", method = RequestMethod.POST)
    public String addCassette(@RequestBody Cassette cassette) {
        cassetteService.addCassette(cassette);
        return "success";
    }

    @RequestMapping(value = "/cassettes/cassette", method = RequestMethod.PUT)
    public String updateCassette(@RequestBody Cassette cassette) {
        return cassetteService.updateCassette(cassette);
    }

    @RequestMapping(value = "/cassettes/{id}", method = RequestMethod.DELETE)
    public String deleteCassette(@PathVariable int id) {
        cassetteService.removeCassette(id);
        return "removed";
    }

    //localhost:8080/cash/out/5000
    @RequestMapping(value = "/cash/out/{amount}", method = RequestMethod.PUT)
    public BanknotesWrapper withdrawMoney(@PathVariable int amount) throws Exception {
        BanknotesWrapper banknotesWrapper = cashService.withdrawMoney(amount);
        return banknotesWrapper;
    }

    //localhost:8080/cash/out/withExchange/5000
    @RequestMapping(value = "/cash/out/withExchange/{amount}", method = RequestMethod.PUT)
    public BanknotesWrapper withdrawMoneyWithExchange(@PathVariable int amount) throws Exception {
        BanknotesWrapper banknotesWrapper = cashService.withdrawMoneyWithExchange(amount);
        return banknotesWrapper;
    }

    //localhost:8080/cash/in
    //body: {"banknotes":[{"denomination":5000,"amount":2,"currency":"RUB"},{"denomination":1000,"amount":5,"currency":"RUB"}]}
    @RequestMapping(value = "/cash/in", method = RequestMethod.PUT)
    public String depositMoney(@RequestBody BanknotesWrapper banknotesWrapper) {
        return cashService.depositMoney(banknotesWrapper);
    }
}

