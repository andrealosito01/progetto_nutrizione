package com.example.pswproject.repositories;

import com.example.pswproject.entities.Peso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PesoRepository extends JpaRepository<Peso,Long> {

}
