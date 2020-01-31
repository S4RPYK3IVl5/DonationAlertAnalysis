package ru.quillaer.daa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.quillaer.daa.domains.Donate;
import ru.quillaer.daa.services.DonationsService;

import java.util.List;

//Контроллер обработки запросов о получении донатов пользователя
@RequestMapping("/api/donations")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
public class DonationsController {

    private final DonationsService donationsService;

    @Autowired
    public DonationsController(DonationsService donationsService) {
        this.donationsService = donationsService;
    }

    @GetMapping
    public ResponseEntity<List<Donate>> getDonations(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok().body(donationsService.getDonations(userDetails));
    }

}
