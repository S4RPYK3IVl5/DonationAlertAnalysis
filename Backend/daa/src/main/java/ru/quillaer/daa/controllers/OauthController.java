package ru.quillaer.daa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.quillaer.daa.domains.Token;
import ru.quillaer.daa.services.OauthService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/api/oauth")
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

    //Запрос, который выполняет всю логику по получению токена и данных польщователя
    @GetMapping("/code")
    public Token codeConsumption(@RequestParam("code") String code) {
        return oauthService.codeConsumption(code);
    }

}
