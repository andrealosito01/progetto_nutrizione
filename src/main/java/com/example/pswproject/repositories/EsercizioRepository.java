package com.example.pswproject.repositories;

import com.example.pswproject.entities.Esercizio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsercizioRepository extends JpaRepository<Esercizio,Long> {
}
