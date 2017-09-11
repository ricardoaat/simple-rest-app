package com.rarc.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.rarc.auth.JwtTokenUtil;
import com.rarc.auth.JwtUser;
import com.rarc.auth.service.UserServiceImpl;
import com.rarc.model.auth.RequestUser;
import com.rarc.model.auth.User;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "user" ,method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }

    @RequestMapping(value = "/auth/registration", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody RequestUser user){
        
        User newUser = new User();
        
        newUser.setEmail(user.getEmail());
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setPassword(user.getPassword());
        newUser.setUsername(user.getUsername());

        if (userServiceImpl.isUserExist(newUser)){
            return ResponseEntity.badRequest().body("User exist");
        }

        userServiceImpl.save(newUser);

        return ResponseEntity.ok(user);
    }

}
