package ru.quillaer.daa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.quillaer.daa.domains.DAUser;
import ru.quillaer.daa.domains.User;
import ru.quillaer.daa.repositories.UserRepository;
import ru.quillaer.daa.security.services.UserPrinciple;

@Service
public class DAUserService {

    private final UserRepository userRepository;

    @Autowired
    public DAUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DAUser getDaUser(UserPrinciple userPrinciple){
        User user = userRepository.findByUsername(userPrinciple.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("No such a user by username: " + userPrinciple.getUsername())
        );
        DAUser daUser = user.getToken().getDaUser();
        return daUser;
    }

}
