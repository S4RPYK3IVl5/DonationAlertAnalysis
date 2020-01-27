package ru.quillaer.daa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.quillaer.daa.domains.DAUser;
import ru.quillaer.daa.security.services.UserPrinciple;
import ru.quillaer.daa.services.OauthService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    @GetMapping("/code")
    public void codeConsumption(@RequestParam("code") String code, HttpServletResponse httpServletResponse,
                                @AuthenticationPrincipal UserPrinciple userPrinciple) throws IOException {
        System.out.println(userPrinciple);
        oauthService.codeConsumption(code, userPrinciple);
        httpServletResponse.sendRedirect("http://localhost:4200/");
    }

}
