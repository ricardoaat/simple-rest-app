package com.rarc.model;

import java.util.stream.Stream;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long > {
    
    @Query("select c from User c where c.username = :username")
    Stream<User> findByNameReturnStream(@Param("username") String username);

}