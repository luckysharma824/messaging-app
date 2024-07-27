package com.funonfire.controller;

import com.funonfire.dto.UserDto;
import com.funonfire.entity.User;
import com.funonfire.service.UserService;
import com.funonfire.util.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Object> user(@RequestBody UserDto userDto) {
        User user = userService.insertUser(userDto);
        return ResponseHandler.response(user, "User inserted successfully", true, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getUser(@RequestParam String msisdn) {
        User user = userService.getUserMsisdn(msisdn);
        return ResponseHandler.response(user, "User fetched Successfully", true, HttpStatus.OK);
    }

}
