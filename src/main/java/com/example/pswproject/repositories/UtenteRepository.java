package com.example.pswproject.repositories;

import com.example.pswproject.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UtenteRepository extends JpaRepository<Utente,Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<Utente> findByUsername(String username);
}
