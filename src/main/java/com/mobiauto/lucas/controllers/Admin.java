package com.mobiauto.lucas.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class Admin {

    @GetMapping()
    public ResponseEntity getAllAdmin() {

        return ResponseEntity.ok("Success!");
    }
}
