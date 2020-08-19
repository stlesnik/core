package com.stlesnik.core.controller;

import com.stlesnik.core.model.Cassette;
import com.stlesnik.core.service.CassetteService;
import com.stlesnik.core.service.WithdrawService;
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
    WithdrawService withdrawService;

    @RequestMapping(value = "/cassettes", method = RequestMethod.GET)
    public List<Cassette> getCassetteList() {
        logger.info("trying list");
        return cassetteService.listCassette();
    }

    @RequestMapping(value = "/cassettes/{id}", method = RequestMethod.GET)
    public Cassette getCassetteById(@PathVariable int id) {
        logger.info("id = " + id);
        return cassetteService.getCassetteById(id);
    }

    //http://localhost:8080/cassettes/1234?name=nnaammee&type=In&value=0
    //http://localhost:8080/cassettes/1234?name=nnaammee&type=Recycling&value=5000
    @RequestMapping(value = "/cassettes/{id}", method = RequestMethod.POST)
    public String addCassette(@PathVariable int id,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "type", required = true) Cassette.CassetteType type,
                              @RequestParam(value =  "value", required = false) Integer value) {
        Cassette cassette = new Cassette();
        cassette.setId(id);
        cassette.setName(name);
        cassette.setType(type);
        if(cassette.getType().equals(Cassette.CassetteType.Recycling)){
            cassette.setValue(value);
        }
        else{cassette.setValue(0);}
        cassetteService.addCassette(cassette);
        return "success";
    }

    @RequestMapping(value = "/cassettes/{id}", method = RequestMethod.PUT)
    public String updateCassette(@PathVariable int id,
                                 @RequestParam(value = "name", required = true) String name,
                                 @RequestParam(value = "type", required = true) Cassette.CassetteType type,
                                 @RequestParam(value =  "value", required = false) int value) {
        Cassette cassette = new Cassette();
        cassette.setId(id);
        cassette.setName(name);
        cassette.setType(type);
        if(cassette.getType().equals(Cassette.CassetteType.Recycling)){
            cassette.setValue(value);
        }
        else{cassette.setValue(0);}
        return cassetteService.updateCassette(cassette);
    }

    @RequestMapping(value = "/cassettes/{id}", method = RequestMethod.DELETE)
    public String deleteCassette(@PathVariable int id) {
        cassetteService.removeCassette(id);
        return "removed";
    }

    //localhost:8080/cash/out/5000
    @RequestMapping(value = "/cash/out/{amount}", method = RequestMethod.GET)
    public String withdrawMoney(@PathVariable int amount) {
        return withdrawService.withdrawMoney(amount);
    }


}
