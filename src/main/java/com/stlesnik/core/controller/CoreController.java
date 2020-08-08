package com.stlesnik.core.controller;

import com.stlesnik.core.model.Cassette;
import com.stlesnik.core.service.CoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoreController {
    Logger logger = LoggerFactory.getLogger(CoreController.class);

    @Autowired
    CoreService service;

    @GetMapping("/atm")
    public String sayAtm(){
        return "Hello! try http://localhost:8080/atm/cassettes";
    }

    @GetMapping("/cassettes")
    public List<Cassette> getCassetteList() {
        logger.info("trying list");
        return service.listCassette();
    }

    @GetMapping("/cassettes/{id}")
    Cassette getCassetteById(@PathVariable int id) {
        logger.info("id = " + id);
        return service.getCassetteById(id);
    }

}
