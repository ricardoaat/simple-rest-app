package com.rarc.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.rarc.auth.repository.UserRepository;
import com.rarc.model.Status;
import com.rarc.model.auth.User;



@RestController
public class ServerStatController {

    @Autowired
    private UserRepository userRepository;  


    @RequestMapping(path = "/status", method = RequestMethod.GET)
    public ResponseEntity<String> getStatus(){
        Status status = new Status();
        status.setStatus("RUNNING BIIIIIIIIIIITCH");
        status.setCode(1);
        System.out.println("Fetched status");
        return new ResponseEntity(status, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String printUsers(){

        for (User user : userRepository.findAll()) {
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            System.out.println(user.getId() + " " + user.getUsername());
        }        
        
        return "PRINTED";
    }    
}