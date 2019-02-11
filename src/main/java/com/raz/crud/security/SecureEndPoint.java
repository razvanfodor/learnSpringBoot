package com.raz.crud.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureEndPoint {

    @GetMapping("/security")
    public String getUser(){
        return  "is this secure?";
    }
}
