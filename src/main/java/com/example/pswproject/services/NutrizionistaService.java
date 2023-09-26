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

    public Nutrizionista aggiorna(String username, Nutrizionista n) throws EmailAlreadyExistsException, UsernameAlreadyExistsException {
        Optional<Nutrizionista> OpNutrizionista = rep.findById(username);
        if(OpNutrizionista.isEmpty())
            return registra(n);

        Nutrizionista nutrizionista = OpNutrizionista.get();
        nutrizionista.setDataDiNascita(n.getDataDiNascita());
        nutrizionista.setFoto(n.getFoto());
        nutrizionista.setPresentazione(n.getPresentazione());
        nutrizionista.setEsperienzaFormazione(n.getEsperienzaFormazione());
        nutrizionista.setSpecializzazione(n.getSpecializzazione());
        nutrizionista.setFilosofia(n.getFilosofia());
        nutrizionista.setServizi(n.getServizi());

        return nutrizionista;
    }

    public Nutrizionista rimuovi(String username) throws UserNotFoundException{
        Optional<Nutrizionista> OpNutrizionista = rep.findById(username);
        if(OpNutrizionista.isEmpty())
            throw new UserNotFoundException();

        Nutrizionista nutrizionista = OpNutrizionista.get();
        rep.delete(nutrizionista);

        return nutrizionista;
    }
}
