package ru.quillaer.daa.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/test")
@RestController
public class TestRestAPIs {

    @GetMapping("user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess(){
        return ">>> User Contents";
    }

    @GetMapping("pm")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public String projectManagerAccess(){
        return ">>> Project Manager Board";
    }

    @GetMapping("admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess(){
        return ">>> Admin Contents";
    }

}
