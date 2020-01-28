package ru.quillaer.daa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.quillaer.daa.domains.Token;
import ru.quillaer.daa.domains.User;
import ru.quillaer.daa.repositories.UserRepository;

import java.util.Date;

//Сервис обработки запросов к TokenController
@Service
public class TokenService {

    private final UserRepository userRepository;

    @Autowired
    public TokenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> getToken(UserDetails userDetails) {

        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException( "No such a user by : " + userDetails.getUsername() )
        );

        Token token = user.getToken();
        if (token == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user has not token");

        System.out.println("System.currentTimeMillis() - user.getToken().getCreation_date().getTime() > 86400000 " + (new Date().getTime() - user.getToken().getCreation_date()));
        if (System.currentTimeMillis() - user.getToken().getCreation_date() > 86400000)
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "http://localhost:8080/api/oauth/refreshtoken").build();

        return ResponseEntity.ok().body(token.getAccess_token());
    }
}
