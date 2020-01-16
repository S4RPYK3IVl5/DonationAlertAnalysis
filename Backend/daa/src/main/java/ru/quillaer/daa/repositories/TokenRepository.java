package ru.quillaer.daa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.quillaer.daa.domains.DAUser;
import ru.quillaer.daa.domains.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    public Token getByDaUser(DAUser daUser);
}
