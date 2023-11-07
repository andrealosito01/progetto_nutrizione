package com.example.pswproject.repositories;

import com.example.pswproject.entities.VoceDiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoceDiarioRepository extends JpaRepository<VoceDiario,Long> {
}
