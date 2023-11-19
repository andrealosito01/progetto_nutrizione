package com.example.pswproject.repositories;

import com.example.pswproject.entities.Passi;
import com.example.pswproject.entities.Utente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PassiRepository extends JpaRepository<Passi,Long> {
    Page<Passi> findAllByUtente(Utente utente, Pageable pageable);
    boolean existsByDataAndUtente(Date data, Utente utente);
}
