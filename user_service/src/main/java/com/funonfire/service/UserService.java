package com.funonfire.service;

import com.funonfire.dto.UserDto;
import com.funonfire.entity.User;
import com.funonfire.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User insertUser(UserDto userDto) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(userDto.getName());
        user.setMsisdn(userDto.getMsisdn());
        return userRepository.save(user);
    }

    public User getUserMsisdn(String msisdn) {
        return userRepository.findByMsisdn(msisdn);
    }

}
