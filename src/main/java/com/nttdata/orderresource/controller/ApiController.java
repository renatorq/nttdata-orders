package com.nttdata.orderresource.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class ApiController {

    @GetMapping("/test")
    public ResponseEntity<String> test(Authentication auth){
        return  ResponseEntity.ok("api success");
    }

}
