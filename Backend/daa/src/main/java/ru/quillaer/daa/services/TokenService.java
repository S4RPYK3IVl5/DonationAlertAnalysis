package ru.quillaer.daa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.quillaer.daa.domains.Token;
import ru.quillaer.daa.domains.User;
import ru.quillaer.daa.repositories.TokenRepository;
import ru.quillaer.daa.repositories.UserRepository;

@Service
public class TokenService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public Token getToken(UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException( "No such a user by : " + userDetails.getUsername() )
        );
        Token token = tokenRepository.findById(user.getToken().getId()).orElseThrow(
                () -> new IllegalArgumentException("No such a token by username : " + user.getUsername() )
        );
        System.out.println(token);
        return token;
    }
}
