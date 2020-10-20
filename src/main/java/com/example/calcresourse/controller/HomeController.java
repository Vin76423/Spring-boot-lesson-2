package com.example.calcresourse.controller;

import com.example.calcresourse.entity.User;
import com.example.calcresourse.repository.UserH2repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "home")
public class HomeController {

    @Autowired
    private UserH2repository userH2repository;



    @PostMapping(path = "reg")
    private ResponseEntity<User> registration(@RequestBody User user) {
        userH2repository.save(user);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("token", String.valueOf(user.getToken()));
        return new ResponseEntity<>(user ,headers, HttpStatus.CREATED);
    }


    @PostMapping(path = "auth")
    private ResponseEntity<User> authorisation(@RequestBody User user) {
        ResponseEntity<User> result;
        if (userH2repository.existsUserByLoginAndPassword(user.getLogin(), user.getPassword())) {
            long token = userH2repository.findByLoginAndPassword(user.getLogin(), user.getPassword()).getToken();
            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.add("token", String.valueOf(token));
            result = new ResponseEntity<>(headers, HttpStatus.CREATED);
        } else {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return result;
    }
}