package ru.quillaer.daa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.quillaer.daa.domains.Role;
import ru.quillaer.daa.domains.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
