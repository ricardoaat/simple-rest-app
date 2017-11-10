package com.rarc.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.rarc.auth.JwtTokenUtil;
import com.rarc.auth.JwtUser;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        String token = jwtTokenUtil.extractToken(request.getHeader(tokenHeader));
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}