package ru.quillaer.daa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.quillaer.daa.domains.DAUser;
import ru.quillaer.daa.domains.Token;
import ru.quillaer.daa.domains.User;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    public Optional<Token> getByDaUser(DAUser daUser);
    public Optional<Token> getByUser(User user);
    public Optional<Token> getById(Long id);
}
