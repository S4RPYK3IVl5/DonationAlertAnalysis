package ru.quillaer.daa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.quillaer.daa.domains.DAUser;
import ru.quillaer.daa.security.services.UserPrinciple;
import ru.quillaer.daa.services.DAUserService;

//Контроллер обработки запросов о получении данных пользователя
@RequestMapping("/api/user")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
public class DAUserController {

    private final DAUserService daUserService;

    @Autowired
    public DAUserController(DAUserService daUserService) {
        this.daUserService = daUserService;
    }

    @GetMapping
    public ResponseEntity<DAUser> getDaUser(@AuthenticationPrincipal UserPrinciple userPrinciple){
        return ResponseEntity.ok().body(this.daUserService.getDaUser(userPrinciple));
    }

}
