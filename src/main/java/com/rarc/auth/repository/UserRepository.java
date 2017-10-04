package com.rarc.auth.repository;

import com.rarc.model.auth.User;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long > {
    
    @Query("select c from User c where c.username = :username")
    Stream<User> findByNameReturnStream(@Param("username") String username);

    User findByUsername(String username);
}