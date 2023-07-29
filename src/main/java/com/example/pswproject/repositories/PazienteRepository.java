package com.example.pswproject.repositories;

import com.example.pswproject.entities.Paziente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PazienteRepository extends JpaRepository<Paziente,String> {

    boolean existsByEmail(String email);
}
