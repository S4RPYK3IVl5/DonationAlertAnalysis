package ru.quillaer.daa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quillaer.daa.domains.DAUser;
import ru.quillaer.daa.repositories.DAUserRepository;

@Service
public class DAUserService {

    private final DAUserRepository daUserRepository;

    @Autowired
    public DAUserService(DAUserRepository daUserRepository) {
        this.daUserRepository = daUserRepository;
    }

    public DAUser getDaUser(String strId){
        DAUser daUser = daUserRepository.getById(Long.valueOf(strId));
        return daUser;
    }

}
