package com.example.pswproject.repositories;

import com.example.pswproject.entities.Passi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassiRepository extends JpaRepository<Passi,Long> {
}
