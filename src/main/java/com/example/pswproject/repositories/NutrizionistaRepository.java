package com.example.pswproject.repositories;

import com.example.pswproject.entities.Nutrizionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutrizionistaRepository extends JpaRepository<Nutrizionista,String>{

    boolean existsByEmail(String email);

}
