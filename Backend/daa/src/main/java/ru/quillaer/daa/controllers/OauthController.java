package ru.quillaer.daa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.quillaer.daa.domains.DAUser;
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

    //Простой запрос, который редиректит на эндпоинт от DA, на котором юзер подтверждает то что он согласен предоставить нам данные
    @GetMapping("/authorize")
    public void getCode(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect(oauthService.getCode());
    }

    //Запрос, который выполняет всю логику по сохранению токена и данных пользователя
    @GetMapping("/code")
    public void codeConsumption(@RequestParam("code") String code, HttpServletResponse httpServletResponse) throws IOException {
        DAUser daUser = oauthService.codeConsumption(code);
        httpServletResponse.sendRedirect("http://localhost:4200/user/" + daUser.getId());
    }

}
