package ru.quillaer.daa.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
public class OauthService {

    private final RestTemplate restTemplate;

    @Value("${client_id}")
    private String client_id;
    @Value("${client_secret}")
    private String client_secret;
    private final String redirect_url = "http://localhost:4200/code";
    private final String scope = "oauth-user-show%20oauth-donation-subscribe%20oauth-donation-index";
    private final Gson gson = new GsonBuilder().create();
    private final TokenRepository tokenRepository;
    private final DAUserRepository daUserRepository;
    private final UserRepository userRepository;

    //Сервис обработки запросов к OauthController
    @Autowired
    public OauthService(RestTemplate restTemplate, TokenRepository tokenRepository, DAUserRepository daUserRepository, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.tokenRepository = tokenRepository;
        this.daUserRepository = daUserRepository;
        this.userRepository = userRepository;
    }


    public void refreshToken(UserPrinciple userPrinciple) {

        User user = userRepository.findByUsername(userPrinciple.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("no such a user ny username: " + userPrinciple.getUsername())
        );
        Token oldToken = user.getToken();

        System.out.println("OauthService (oldToken) 59 -> " + oldToken);

        String req = "curl -X POST https://www.donationalerts.com/oauth/token -H Content-Type:application/x-www-form-urlencoded -d grant_type=refresh_token"
                + "&refresh_token=" + oldToken.getRefresh_token() + "&client_id=" + client_id
                + "&client_secret=" + client_secret + "&scope=" + scope;
        StringBuilder stringBuilder = getTokenWithCode(req);
        Token newToken = gson.fromJson(stringBuilder.toString(), Token.class);
        newToken.setCreation_date(new Date().getTime());

        newToken.setId(oldToken.getId());
        tokenRepository.save(newToken);

        System.out.println("OauthService (newToken) 59 -> " + newToken);

    }

    public void codeConsumption(String code, UserPrinciple userPrinciple) {

        String req = "curl -X POST https://www.donationalerts.com/oauth/token -H Content-Type:application/x-www-form-urlencoded -d grant_type=authorization_code&client_id=" + client_id
                + "&client_secret=" + client_secret + "&redirect_uri=" + redirect_url + "&code=" + code;

        StringBuilder stringBuilder = getTokenWithCode(req);
        Token token = gson.fromJson(stringBuilder.toString(), Token.class);
        token.setCreation_date(System.currentTimeMillis());

        User user = userRepository.findByUsername(userPrinciple.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("No such a user by username : " + userPrinciple.getUsername())
        );

        if(user.getToken() == null){
            tokenRepository.save(token);
            user.setToken(token);
            userRepository.save(user);
        }

    }

    //Тут по полученному коду отправляем curl запрос на энд поинт DA для получения токена и записываем данные в StringBuilder
    private StringBuilder getTokenWithCode(String req) {
        StringBuilder stringBuilder = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();

        try {
            Process process = runtime.exec(req);
            InputStream inputStream = process.getInputStream();
            while(true){
                int x = inputStream.read();
                if (x == -1)
                    break;
                stringBuilder.append((char)x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

}
