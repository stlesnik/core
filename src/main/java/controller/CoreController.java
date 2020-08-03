package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.CoreService;
import model.Cassette;

import java.util.List;

@RestController
@RequestMapping("/atm")
public class CoreController {
    @Autowired
    CoreService service;

    @GetMapping
    public ResponseEntity<List<Cassette>> getAllCassettes() {
        List<Cassette> list = service.listCassette();

        return new ResponseEntity<List<Cassette>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
