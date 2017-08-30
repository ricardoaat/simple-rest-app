package com.rarc.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.rarc.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.rarc.model.User;


@RestController
public class Status {

    @Autowired
    private UserRepository userRepository;  


    @RequestMapping(path = "/status", method = RequestMethod.GET)
    public String getStatus(){

        for (User user : userRepository.findAll()) {
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            System.out.println(user);
        }        
        
        return "RUNNING BIIIIIIIIIIITCH";
    }
}