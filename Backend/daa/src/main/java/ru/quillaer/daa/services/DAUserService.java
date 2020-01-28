package ru.quillaer.daa.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.quillaer.daa.domains.DAUser;
import ru.quillaer.daa.domains.Token;
import ru.quillaer.daa.domains.User;
import ru.quillaer.daa.repositories.DAUserRepository;
import ru.quillaer.daa.repositories.TokenRepository;
import ru.quillaer.daa.repositories.UserRepository;
import ru.quillaer.daa.security.services.UserPrinciple;

//Сервис обработки запросов к DAUserController
@Service
public class DAUserService {

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final DAUserRepository daUserRepository;
    private final Gson gson = new GsonBuilder().create();

    @Autowired
    public DAUserService(RestTemplate restTemplate, UserRepository userRepository, TokenRepository tokenRepository, DAUserRepository daUserRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.daUserRepository = daUserRepository;
    }

    public DAUser getDaUser(UserPrinciple userPrinciple){
        User user = userRepository.findByUsername(userPrinciple.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("No such a user by username: " + userPrinciple.getUsername())
        );
        Token token = user.getToken();

        DAUser daUser = token.getDaUser();
        if (daUser == null) {
            daUser = getDaUserFromAPI(user.getToken());
            daUserRepository.save(daUser);
            tokenRepository.save(token);
        }

        return daUser;
    }

    public DAUser updateDaUser(UserPrinciple userPrinciple) {
        User user = userRepository.findByUsername(userPrinciple.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("No such a user by username: " + userPrinciple.getUsername())
        );
        Token token = user.getToken();

        DAUser daUser = getDaUserFromAPI(token);
        token.setDaUser(daUser);
        daUserRepository.save(daUser);
        tokenRepository.save(token);
        return daUser;
    }

    //Получаем DAUser'a с помощью отправления токена на эндпоинт DA
    private DAUser getDaUserFromAPI(Token token){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token.getAccess_token());
        HttpEntity req = new HttpEntity(httpHeaders);
        String url = "https://www.donationalerts.com/api/v1/user/oauth";

        ResponseEntity<String> daUserResponseEntity = this.restTemplate.exchange(url, HttpMethod.GET, req, String.class, 1);
        JSONObject jsonObject = new JSONObject(daUserResponseEntity.getBody());
        return gson.fromJson(jsonObject.getJSONObject("data").toString(), DAUser.class);

    }
}
