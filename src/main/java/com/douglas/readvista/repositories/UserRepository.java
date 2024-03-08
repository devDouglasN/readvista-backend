package com.douglas.readvista.repositories;

import com.douglas.readvista.entities.UserSS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserSS, Integer> {

    UserDetails findByEmail(String email);

	boolean existsByEmail(String email);
}
