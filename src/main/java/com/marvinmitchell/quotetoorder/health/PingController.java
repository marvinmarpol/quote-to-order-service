package com.marvinmitchell.quotetoorder.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class PingController {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/ping")
    public ResponseEntity<Object> requestMethodName() {
        return new ResponseEntity<>(appName + " " + HttpStatus.OK.toString(), HttpStatus.OK);
    }

}
