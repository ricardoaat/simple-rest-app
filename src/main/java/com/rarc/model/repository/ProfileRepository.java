package com.rarc.model.repository;

import com.rarc.model.Profile;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

    @Override
    @RestResource(exported=false)
    public void delete(Long id);

    //@Query("select c from Profile c where c.user = :userid")
    //Stream<Profile> findByUserIdReturnStream(@Param("userid") Long userid);

    List<Profile> findByUser_Id(Long id); 
}
