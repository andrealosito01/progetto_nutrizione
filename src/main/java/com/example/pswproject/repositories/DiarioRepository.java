package com.example.pswproject.repositories;

import com.example.pswproject.entities.Diario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiarioRepository extends JpaRepository<Diario,Long> {
}
