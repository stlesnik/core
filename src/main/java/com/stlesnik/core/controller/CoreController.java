package com.stlesnik.core.controller;

import com.stlesnik.core.model.Cassette;
import com.stlesnik.core.service.CoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoreController {
    Logger logger = LoggerFactory.getLogger(CoreController.class);

    @Autowired
    CoreService service;

    @RequestMapping(value = "/cassettes", method = RequestMethod.GET)
    public List<Cassette> getCassetteList() {
        logger.info("trying list");
        return service.listCassette();
    }

    @RequestMapping(value = "/cassettes/{id}", method = RequestMethod.GET)
    public Cassette getCassetteById(@PathVariable int id) {
        logger.info("id = " + id);
        return service.getCassetteById(id);
    }
    //http://localhost:8080/cassettes/1234?name=nnaammee&type=In
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
        service.addCassette(cassette);
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
            cassette.setValue(cassette.getValue());
        }
        return service.updateCassette(cassette);
    }

    @RequestMapping(value = "/cassettes/{id}", method = RequestMethod.DELETE)
    public String deleteCassette(@PathVariable int id) {
        service.removeCassette(id);
        return "removed";
    }

    //localhost:8080/cash/out/5000
    @RequestMapping(value = "/cash/out/{amount}", method = RequestMethod.GET)
    public String withdrawMoney(@PathVariable int amount){
        if(amount % 100 != 0){
            return "You can only withdraw a multiple of 100!";
        }
        else{
            int temp = amount;

            int[][] notes = {{5000, 0}, {2000, 0}, {1000, 0}, {500, 0}, {200, 0}, {100, 0}};
            for(int i = 0; i<6; i++){
                notes[i][1] = temp / notes[i][0];
                int numOfNotes = service.getNumOfNotes(notes[i][0]);
                if(numOfNotes>=notes[i][1]){temp = temp - notes[i][0] * notes[i][1];}
                else{
                    temp = temp - notes[i][0] * numOfNotes;
                    notes[i][1] = numOfNotes;
                }
            }
            if(temp!=0){return "it is impossible to issue the amount";}
            else{
                service.withdrawCounters(notes);
                return "Success " +System.lineSeparator() +
                        "Сумма снятия - " + amount +System.lineSeparator() +
                        " Кол-во купюр 5000:" + notes[0][1] + System.lineSeparator() +
                        " Кол-во купюр 2000:" + notes[1][1] +System.lineSeparator()+
                        " Кол-во купюр 1000:" + notes[2][1] +System.lineSeparator() +
                        " Кол-во купюр  500:" + notes[3][1] +System.lineSeparator() +
                        " Кол-во купюр  200:" + notes[4][1] +System.lineSeparator() +
                        " Кол-во купюр  100:" + notes[5][1];
            }
        }
    }


}
