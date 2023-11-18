package com.example.pswproject.repositories;

import com.example.pswproject.entities.Misura;
import com.example.pswproject.entities.Utente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MisuraRepository extends JpaRepository<Misura,Long> {
    Page<Misura> findAllByUtente(Utente utente, Pageable pageable);
}
