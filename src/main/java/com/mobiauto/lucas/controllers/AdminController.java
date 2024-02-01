package com.mobiauto.lucas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobiauto.lucas.domain.Admin.Admin;
import com.mobiauto.lucas.domain.Admin.AdminRepository;
import com.mobiauto.lucas.domain.Admin.AdminRequest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping
    public ResponseEntity getAllAdmin() {
        var administrator = adminRepository.findAll();

        return ResponseEntity.ok(administrator);
    }

    @PostMapping
    public ResponseEntity<String> createdAdmin(@RequestBody @Valid AdminRequest data) {

        Admin newAdmin = new Admin(data);

        adminRepository.save(newAdmin);

        return ResponseEntity.ok("Admin cadastrado com sucesso!");
    }

}
