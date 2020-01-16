package ru.quillaer.daa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.quillaer.daa.domains.DAUser;

@Repository
public interface DAUserRepository extends JpaRepository<DAUser, Long> {
    public DAUser getById(Long id);
}
