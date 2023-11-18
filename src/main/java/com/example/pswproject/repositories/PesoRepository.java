package com.example.pswproject.repositories;

import com.example.pswproject.entities.Peso;
import com.example.pswproject.entities.Utente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PesoRepository extends JpaRepository<Peso,Long> {
    Page<Peso> findAllByUtente(Utente utente, Pageable pageable);
}
