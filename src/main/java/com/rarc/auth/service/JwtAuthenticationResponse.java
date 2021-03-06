package com.rarc.auth.service;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {
    
        private static final long serialVersionUID = 2575319785544993652L;
    
        private final String token;
    
        public JwtAuthenticationResponse(String token) {
            this.token = token;
        }
    
        public String getToken() {
            return this.token;
        }
    }