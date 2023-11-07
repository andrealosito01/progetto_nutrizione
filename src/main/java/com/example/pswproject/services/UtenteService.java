package com.example.pswproject.services;

import com.example.pswproject.entities.Utente;
import com.example.pswproject.repositories.UtenteRepository;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.EmailAlreadyExistsException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import com.example.pswproject.support.exceptions.UsernameAlreadyExistsException;
import com.example.pswproject.support.keycloak.AccountsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private AccountsManager accountsManager;

   public Utente aggiungi(Utente u) throws UsernameAlreadyExistsException, EmailAlreadyExistsException, BadRequestException {
       if(isNotValid(u))
           throw new BadRequestException();

        if(utenteRepository.existsByUsername(u.getUsername()))
            throw new UsernameAlreadyExistsException();
        if(utenteRepository.existsByEmail(u.getEmail()))
            throw new EmailAlreadyExistsException();

        accountsManager.registraSuKeycloak(u.getUsername(),u.getEmail(),u.getPassword(),"paziente");

        return utenteRepository.save(u);
    }

    private boolean isNotValid(Utente utente){
       return (utente.getId() != null)||(utente.getPiano() != null)||(!utente.getPesi().isEmpty())||(!utente.getMisure().isEmpty())
               ||(!utente.getAlimenti().isEmpty())||(!utente.getDiari().isEmpty());
    }

    @Transactional(readOnly = true)
    public Utente getUtente(String username) throws ResourceNotFoundException {
        Optional<Utente> res = utenteRepository.findByUsername(username);
        if(res.isEmpty())
            throw new ResourceNotFoundException();
        return res.get();
    }

    @Transactional(readOnly = true)
    public Collection<Utente> getUtenti(){
        return utenteRepository.findAll();
    }

    public Utente modifica(String username, Utente u) throws ResourceNotFoundException, BadRequestException {
       if(isNotValid(u))
           throw new BadRequestException();
        Optional<Utente> opUtente = utenteRepository.findByUsername(username);
        if(opUtente.isEmpty()) {
            // ovviamente ci aspettiamo di non entrare mai in questo blocco
            throw new ResourceNotFoundException();
        }
        Utente utente = opUtente.get();
        utente.setNome(u.getNome());
        utente.setCognome(u.getCognome());
        utente.setDataDiNascita(u.getDataDiNascita());
        utente.setAltezza(u.getAltezza());

        return utente;
    }

    public Utente rimuovi(String username) throws ResourceNotFoundException{
        Optional<Utente> opUtente = utenteRepository.findByUsername(username);
        if(opUtente.isEmpty())
            throw new ResourceNotFoundException();

        if(!accountsManager.rimuoviDaKeycloak(username))
            throw new ResourceNotFoundException();

        Utente utente = opUtente.get();
        utenteRepository.delete(utente);

        return utente;
    }


}
