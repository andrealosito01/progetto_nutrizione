package com.example.pswproject.services;

import com.example.pswproject.entities.Piano;
import com.example.pswproject.entities.Utente;
import com.example.pswproject.repositories.PianoRepository;
import com.example.pswproject.repositories.UtenteRepository;
import com.example.pswproject.support.exceptions.BadRequestException;
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
    private UtenteService utenteService;

    @Autowired
    private PianoRepository pianoRepository;

    @Transactional(readOnly = true)
    public Piano getPiano(String username) throws ResourceNotFoundException {
        Utente utente = utenteService.getUtente(username);
        return utente.getPiano();
    }

    public Piano aggiungi(String username, Piano piano) throws PianoAlreadyExistsException, ResourceNotFoundException, BadRequestException {
        if(isNotValid(piano))
            throw new BadRequestException();

        // non uso this.getPiano(username) perchè mi serve l'utente per chiamare setPiano(piano)
        Utente utente = utenteService.getUtente(username);
        if(utente.getPiano() != null)
            throw new PianoAlreadyExistsException();

        utente.setPiano(piano);
        return pianoRepository.save(piano);
    }

    private boolean isNotValid(Piano piano){
        return piano.getId() != null;
    }

    public Piano modifica(String username, Piano piano) throws ResourceNotFoundException, BadRequestException {
        if(isNotValid(piano))
            throw new BadRequestException();

        Utente utente = utenteService.getUtente(username);
        pianoRepository.delete(utente.getPiano());
        utente.setPiano(piano);

        return pianoRepository.save(piano);
    }

    public Piano rimuovi(String username) throws ResourceNotFoundException {
        Utente utente = utenteService.getUtente(username);
        Piano piano = utente.getPiano();
        if(piano == null)
            throw new ResourceNotFoundException();

        pianoRepository.delete(piano);
        utente.setPiano(null);
        return piano;
    }



}
