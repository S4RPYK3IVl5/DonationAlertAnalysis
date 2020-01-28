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
import java.sql.Date;
import java.sql.Timestamp;

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

        String req = "curl -X POST https://www.donationalerts.com/oauth/token -H Content-Type:application/x-www-form-urlencoded -d grant_type=refresh_token"
                + "&refresh_token=" + oldToken.getRefresh_token() + "&client_id=" + client_id
                + "&client_secret=" + client_secret + "&scope=" + scope;
        StringBuilder stringBuilder = getTokenWithCode(req);
        Token newToken = gson.fromJson(stringBuilder.toString(), Token.class);

        if (oldToken.getDaUser() != null)
            daUserRepository.delete(oldToken.getDaUser());
        tokenRepository.delete(oldToken);

        user.setToken(newToken);
        userRepository.save(user);
    }

    public void codeConsumption(String code, UserPrinciple userPrinciple) {

        String req = "curl -X POST https://www.donationalerts.com/oauth/token -H Content-Type:application/x-www-form-urlencoded -d grant_type=authorization_code&client_id=" + client_id
                + "&client_secret=" + client_secret + "&redirect_uri=" + redirect_url + "&code=" + code;

        StringBuilder stringBuilder = getTokenWithCode(req);
        Token token = gson.fromJson(stringBuilder.toString(), Token.class);
//        DAUser daUser = getDAUser(token);
        token.setCreation_date(System.currentTimeMillis());

        User user = userRepository.findByUsername(userPrinciple.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("No such a user by username : " + userPrinciple.getUsername())
        );

        //если юзер по такому токену уже есть в бд, то мы его не будем сохранять
//        DAUser isDAUser = daUserRepository.getById(daUser.getId()).orElse(
//                null
//        );
//        if (isDAUser == null) {
//            daUserRepository.save(daUser);
//            token.setDaUser(daUser);
//            tokenRepository.save(token);
//        }

//        System.out.println("System.currentTimeMillis() - user.getToken().getCreation_date().getTime() : " + (System.currentTimeMillis() - user.getToken().getCreation_date().getTime()));

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

    //Получаем DAUser'a с помощью отправления токена на эндпоинт DA
    private DAUser getDAUser(Token token){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token.getAccess_token());
        HttpEntity req = new HttpEntity(httpHeaders);
        String url = "https://www.donationalerts.com/api/v1/user/oauth";

        ResponseEntity<String> daUserResponseEntity = this.restTemplate.exchange(url, HttpMethod.GET, req, String.class, 1);
        JSONObject jsonObject = new JSONObject(daUserResponseEntity.getBody());
        return gson.fromJson(jsonObject.getJSONObject("data").toString(), DAUser.class);

    }

}
