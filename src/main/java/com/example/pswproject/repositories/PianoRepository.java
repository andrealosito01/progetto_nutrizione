package com.example.pswproject.repositories;

import com.example.pswproject.entities.Piano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PianoRepository extends JpaRepository<Piano,Long> {
}
