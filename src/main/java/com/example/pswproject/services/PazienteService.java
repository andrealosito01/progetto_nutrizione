package com.example.pswproject.services;

import com.example.pswproject.entities.Paziente;
import com.example.pswproject.repositories.PazienteRepository;
import com.example.pswproject.support.exceptions.EmailAlreadyExistsException;
import com.example.pswproject.support.exceptions.UserNotFoundException;
import com.example.pswproject.support.exceptions.UsernameAlreadyExistsException;
import com.example.pswproject.support.keycloak.AccountsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class PazienteService {

    @Autowired
    PazienteRepository pazienteRepository;

    @Autowired
    AccountsManager accountsManager;

   @Transactional
    public Paziente registra(Paziente p) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {

        if(pazienteRepository.existsById(p.getUsername()))
            throw new UsernameAlreadyExistsException();
        if(pazienteRepository.existsByEmail(p.getEmail()))
            throw new EmailAlreadyExistsException();

        accountsManager.registraSuKeycloak(p.getUsername(),p.getEmail(),p.getPassword(),"paziente");

        return pazienteRepository.save(p);
    }

    @Transactional(readOnly = true)
    public Paziente getPaziente(String username) throws UserNotFoundException {
        Optional<Paziente> res = pazienteRepository.findById(username);
        if(res.isEmpty())
            throw new UserNotFoundException();
        return res.get();
    }

    @Transactional(readOnly = true)
    public Collection<Paziente> getPazienti(){
        return pazienteRepository.findAll();
    }

}
