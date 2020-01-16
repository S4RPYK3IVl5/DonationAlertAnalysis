package ru.quillaer.daa.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.internal.util.xml.impl.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.quillaer.daa.dto.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

@Service
public class OauthService {

    private final RestTemplate restTemplate;

    @Value("${client_id}")
    private String client_id;
    @Value("${client_secret}")
    private String client_secret;
    private final String redirect_url = "http://localhost:8080/api/oauth/code";
    private final String scope = "oauth-user-show%20oauth-donation-subscribe%20oauth-donation-index";

    @Autowired
    public OauthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String getCode() {
        return "https://www.donationalerts.com/oauth/authorize?client_id=" + client_id + "&redirect_uri=" + redirect_url + "&response_type=code&scope=" + scope;
    }

    public Token codeConsumption(String code) {

        Gson gson = new GsonBuilder().create();
        StringBuilder stringBuilder = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();

        String req = "curl -X POST https://www.donationalerts.com/oauth/token -H Content-Type:application/x-www-form-urlencoded -d grant_type=authorization_code&client_id=341&client_secret=QTqjGtl6YtGmll1UUpPQshpzbSBA0K4cP5R1dU2q&redirect_uri=http://localhost:8080/api/oauth/code&code=" + code + "";

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

        Token token = gson.fromJson(stringBuilder.toString(), Token.class);
        System.out.println(token);

        return token;

    }
}
