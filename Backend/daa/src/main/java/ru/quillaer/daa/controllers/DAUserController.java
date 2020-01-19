package ru.quillaer.daa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.quillaer.daa.domains.DAUser;
import ru.quillaer.daa.services.DAUserService;

//Контроллер обработки запросов о получении данных пользователя
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:4200")
@RestController
public class DAUserController {

    private final DAUserService daUserService;

    @Autowired
    public DAUserController(DAUserService daUserService) {
        this.daUserService = daUserService;
    }

    @GetMapping
    public DAUser getDaUser(@RequestParam("id") String strId){
        return this.daUserService.getDaUser(strId);
    }

}
