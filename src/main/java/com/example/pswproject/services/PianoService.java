package com.example.pswproject.services;

import com.example.pswproject.entities.Piano;
import com.example.pswproject.entities.Utente;
import com.example.pswproject.repositories.PianoRepository;
import com.example.pswproject.repositories.UtenteRepository;
import com.example.pswproject.support.exceptions.PianoAlreadyExistsException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PianoService {

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    PianoRepository pianoRepository;

    @Transactional(readOnly = true)
    public Piano getPiano(String username) throws ResourceNotFoundException {
        Optional<Utente> opUtente = utenteRepository.findByUsername(username);
        if(opUtente.isEmpty())
            throw new ResourceNotFoundException();

        Utente utente = opUtente.get();
        Piano piano = utente.getPiano();

        if(piano == null)
            throw new ResourceNotFoundException();

        return piano;
    }

    public Piano aggiungi(String username, Piano piano) throws PianoAlreadyExistsException, ResourceNotFoundException {
        Optional<Utente> opUtente = utenteRepository.findByUsername(username);
        if(opUtente.isEmpty())
            throw new ResourceNotFoundException();

        Utente utente = opUtente.get();
        if(utente.getPiano() != null)
            throw new PianoAlreadyExistsException();

        utente.setPiano(piano);
        return pianoRepository.save(piano);
    }

    public Piano modifica(String username, Piano piano) throws ResourceNotFoundException {
        Optional<Utente> opUtente = utenteRepository.findByUsername(username);
        if(opUtente.isEmpty())
            throw new ResourceNotFoundException();

        Utente utente = opUtente.get();
        pianoRepository.delete(utente.getPiano());
        utente.setPiano(piano);

        return pianoRepository.save(piano);
    }

    public Piano rimuovi(String username) throws ResourceNotFoundException {
        Optional<Utente> opUtente = utenteRepository.findByUsername(username);
        if(opUtente.isEmpty())
            throw new ResourceNotFoundException();

        Utente utente = opUtente.get();
        Piano piano = utente.getPiano();
        if(piano == null)
            throw new ResourceNotFoundException();

        pianoRepository.delete(piano);
        utente.setPiano(null);
        return piano;
    }



}
