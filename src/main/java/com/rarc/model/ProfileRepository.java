package com.rarc.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

    @Override
    @RestResource(exported=false)
    public void delete(Long id);

   
}
