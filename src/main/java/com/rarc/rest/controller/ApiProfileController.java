package com.rarc.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.rarc.auth.JwtTokenUtil;
import com.rarc.auth.JwtUser;
import com.rarc.auth.service.UserServiceImpl;
import com.rarc.model.Profile;
import com.rarc.model.auth.User;
import com.rarc.model.repository.ProfileRepository;



@RestController
@RequestMapping("/api/profile")
public class ApiProfileController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value="profiles", method = RequestMethod.GET)
    public ResponseEntity<List<Profile>> getProfiles() {
        List<Profile> profileList = new ArrayList<>();
        Iterable<Profile> profiles = profileRepository.findAll();
        profiles.iterator().forEachRemaining(profileList::add);
        if(profileList.isEmpty()) {
            return new ResponseEntity<List<Profile>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Profile>>(profileList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Profile>> getProfile(@RequestHeader(value="Authorization") String token) throws Exception {
        token = jwtTokenUtil.extractToken(token);
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(
            jwtTokenUtil.getUsernameFromToken(token));

            User user = userServiceImpl.findByUserName(jwtUser.getUsername());
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } 
        List<Profile> profile = profileRepository.findByUser_Id(user.getId());
        
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Profile> setProfile(@RequestBody Profile profile,
        @RequestHeader(value="Authorization") String token){
        token = jwtTokenUtil.extractToken(token);
        String username = jwtTokenUtil.getUsernameFromToken(token);

        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(username);

        User user = userServiceImpl.findByUserName(jwtUser.getUsername());

        if (user == null){
            return new ResponseEntity<Profile>(HttpStatus.NO_CONTENT);
        }
        profile.setUser(userServiceImpl.findOneById(user.getId()));
        profileRepository.save(profile);

        return new ResponseEntity<Profile>(profile, HttpStatus.OK);
    }

    @RequestMapping(value="excep", method = RequestMethod.POST)
    public ResponseEntity<?> testException() throws Exception {
        throw new Exception("Le exception ");
    }
}