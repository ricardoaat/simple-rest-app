package com.rarc.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Status {
    @RequestMapping(path = "/status", method = RequestMethod.GET)
    public static String getStatus(){
        return "RUNNING BIIIIIIIIIIITCH";
    }
}