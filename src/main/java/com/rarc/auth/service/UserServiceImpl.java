package com.rarc.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rarc.auth.repository.AuthorityRepository;
import com.rarc.auth.repository.UserRepository;
import com.rarc.model.auth.Authority;
import com.rarc.model.auth.AuthorityName;
import com.rarc.model.auth.User;
import com.rarc.utils.TimeProvider;

@Service
public class UserServiceImpl {

    @Autowired
    TimeProvider timeProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    public void save(User user) {
        Authority userAuth = new Authority();
        userAuth.setName(AuthorityName.ROLE_USER);
        Example<Authority> example = Example.of(userAuth);

        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(authorityRepository.findAll(example));
        user.setLastPasswordResetDate(timeProvider.now());

        userRepository.save(user);
    }

    public boolean isUserExist(User user) {
        User lightUser = new User();
        lightUser.setUsername(user.getUsername());
        Example<User> exampleUser = Example.of(lightUser);

        return userRepository.exists(exampleUser);
    }
}