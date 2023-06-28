package com.sarchi.lenderapi.service;

import com.sarchi.lenderapi.domain.CreateUserRequest;
import com.sarchi.lenderapi.domain.User;
import com.sarchi.lenderapi.exceptions.BadRequestException;
import com.sarchi.lenderapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    UserRepository userRepository;

    public User createUser(CreateUserRequest request) {
        try {
            var exists = userRepository.findByMsisdn(request.getMsisdn());
            if (exists.isPresent()) {
                throw new BadRequestException("User already exists by that phone number");
            }

            User user = new User();
            user.setMsisdn(request.getMsisdn());
            user.setCountryCode(request.getCountryCode());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());

            var savedUser = userRepository.save(user);
            return savedUser;
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }

    }
}
