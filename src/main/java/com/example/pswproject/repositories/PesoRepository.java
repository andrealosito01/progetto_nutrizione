package com.example.pswproject.repositories;

import com.example.pswproject.entities.Paziente;
import com.example.pswproject.entities.Peso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PesoRepository extends JpaRepository<Peso,Long> {

    boolean existsByDataAndPaziente(Date data, Paziente paziente);
    List<Peso> findAllByPaziente(Paziente paziente);
    Optional<Peso> findByDataAndPaziente(Date data, Paziente paziente);
}
