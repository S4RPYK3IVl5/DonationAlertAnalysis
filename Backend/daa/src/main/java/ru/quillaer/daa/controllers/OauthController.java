package ru.quillaer.daa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.quillaer.daa.security.services.UserPrinciple;
import ru.quillaer.daa.services.OauthService;

// Контроллеп для обработки запросов авторизации
@RequestMapping("/api/oauth")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
public class OauthController {

    private final OauthService oauthService;

    @Autowired
    public OauthController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    //Запрос, который выполняет всю логику по сохранению токена и данных пользователя
    @PostMapping("/code")
    public void codeConsumption(@RequestBody String code,
                                @AuthenticationPrincipal UserPrinciple userPrinciple) {
        System.out.println("OauthController -> 26 : " + code);
        oauthService.codeConsumption(code, userPrinciple);
    }

    //Запрос об обновлении токена, по истечнии срока хранения
    @GetMapping("/refreshtoken")
    public void refreshToken(@AuthenticationPrincipal UserPrinciple userPrinciple){
        oauthService.refreshToken(userPrinciple);
    }

}
