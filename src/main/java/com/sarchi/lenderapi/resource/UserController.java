package com.sarchi.lenderapi.resource;

import com.sarchi.lenderapi.domain.User;
import com.sarchi.lenderapi.domain.CreateUserRequest;
import com.sarchi.lenderapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    UserService userService;
    @PostMapping
    public User createUser(@RequestBody CreateUserRequest request) throws Exception {
        return  userService.createUser(request);
    }
}
