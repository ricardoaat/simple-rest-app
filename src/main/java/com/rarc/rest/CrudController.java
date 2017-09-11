package com.rarc.rest;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.rarc.auth.repository.UserRepository;
import com.rarc.model.Profile;
import com.rarc.model.ProfileRepository;


@RestController
@RequestMapping("/api")
public class CrudController {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="getProf", method = RequestMethod.GET)
    public ResponseEntity<List<Profile>> getProfiles() {
        List<Profile> profileList = new ArrayList<>();
        Iterable<Profile> profiles = profileRepository.findAll();
        profiles.iterator().forEachRemaining(profileList::add);
        if(profileList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Profile>>(profileList, HttpStatus.OK);
    }

    @RequestMapping(value="/createprof", method = RequestMethod.POST)
    public Profile setProfile(@RequestBody Profile profile){
        profile.setUser(userRepository.findOne(1L));
        profileRepository.save(profile);
        return profile;
    }
}