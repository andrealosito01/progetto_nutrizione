package com.example.pswproject.repositories;

import com.example.pswproject.entities.Misura;
import com.example.pswproject.entities.Utente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface MisuraRepository extends JpaRepository<Misura,Long> {
    Page<Misura> findAllByUtente(Utente utente, Pageable pageable);
    Optional<Misura> findByIdNotAndUtenteAndData(Long id, Utente utente, Date data);
    Optional<Misura> findByIdAndUtente(Long id, Utente utente);
    boolean existsByDataAndUtente(Date data, Utente utente);
}
