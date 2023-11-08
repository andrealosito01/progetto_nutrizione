package com.example.pswproject.repositories;

import com.example.pswproject.entities.Scheda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedaRepository extends JpaRepository<Scheda,Long> {
}
