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
    //http://localhost:8080/cassettes/1234?name=added_through_addline&description=description_haha&type=In
    @RequestMapping(value = "/cassettes/{id}", method = RequestMethod.POST)
    public String addCassette(@PathVariable int id,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "description", required = true) String description,
                              @RequestParam(value = "type", required = true) Cassette.CassetteType type) {
        Cassette cassette = new Cassette();
        cassette.setId(id);
        cassette.setName(name);
        if(!description.equals("")){cassette.setDescription(description);}
        cassette.setType(type);
        service.addCassette(cassette);
        return "success";
    }

    @RequestMapping(value = "/cassettes/{id}", method = RequestMethod.PUT)
    public String updateCassette(@PathVariable int id,
                                 @RequestParam(value = "name", required = true) String name,
                                 @RequestParam(value = "description", required = true) String description,
                                 @RequestParam(value = "type", required = true) Cassette.CassetteType type) {
        Cassette cassette = new Cassette();
        cassette.setId(id);
        cassette.setName(name);
        cassette.setDescription(description);
        cassette.setType(type);
        return service.updateCassette(cassette);
    }

    @RequestMapping(value = "/cassettes/{id}", method = RequestMethod.DELETE)
    public String deleteCassette(@PathVariable int id) {
        service.removeCassette(id);
        return "removed";
    }


}
