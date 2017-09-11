package com.rarc.auth.repository;

import com.rarc.model.auth.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    
}