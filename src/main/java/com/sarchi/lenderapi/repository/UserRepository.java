package com.sarchi.lenderapi.repository;

import com.sarchi.lenderapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Long> {

    Optional<User> findByMsisdn(String msisdn);
}
