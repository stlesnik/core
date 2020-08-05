package com.stlesnik.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atm")
public class CoreController {

    @GetMapping
    public String sayAtm(){
        return "atm";
    }
}
