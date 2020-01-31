package ru.quillaer.daa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.quillaer.daa.domains.Donate;

public interface DonationRepository extends JpaRepository<Donate, Long> {
}
