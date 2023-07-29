package com.example.pswproject.services;

import com.example.pswproject.entities.Nutrizionista;
import com.example.pswproject.repositories.NutrizionistaRepository;
import com.example.pswproject.support.exceptions.EmailAlreadyExistsException;
import com.example.pswproject.support.exceptions.UserNotFoundException;
import com.example.pswproject.support.exceptions.UsernameAlreadyExistsException;
import com.example.pswproject.support.keycloak.AccountsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class NutrizionistaService {

    @Autowired
    NutrizionistaRepository rep;

    @Autowired
    AccountsManager accountsManager;

    public Nutrizionista registra(Nutrizionista n) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        if(rep.existsById(n.getUsername()))
            throw new UsernameAlreadyExistsException();
        if(rep.existsByEmail(n.getEmail()))
            throw new EmailAlreadyExistsException();

        accountsManager.registraSuKeycloak(n.getUsername(),n.getEmail(),n.getPassword(),"nutrizionista");

        return rep.save(n);
    }

    @Transactional(readOnly = true)
    public Nutrizionista getNutrizionista(String username) throws UserNotFoundException {
        Optional<Nutrizionista> res = rep.findById(username);
        if(res.isEmpty())
            throw new UserNotFoundException();
        return res.get();
    }

}
