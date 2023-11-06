package com.example.pswproject.services;

import com.example.pswproject.entities.Peso;
import com.example.pswproject.entities.Utente;
import com.example.pswproject.repositories.UtenteRepository;
import com.example.pswproject.repositories.PesoRepository;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import com.example.pswproject.support.exceptions.WeightAlreadyInsertedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PesoService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PesoRepository pesoRepository;

    public Peso aggiungi(Peso peso, String username) throws WeightAlreadyInsertedException, ResourceNotFoundException {

        Optional<Utente> opUtente = utenteRepository.findByUsername(username);
        if(opUtente.isEmpty())
            throw new ResourceNotFoundException();

        Utente utente = opUtente.get();
        // NON devono essere presenti due pesi dello stesso utente nello stesso giorno
        for(Peso p:utente.getPesi()){
            if(peso.getData().equals(p.getData()))
                throw new WeightAlreadyInsertedException();
        }

        utente.getPesi().add(peso);
        return pesoRepository.save(peso);
    }

    @Transactional(readOnly = true)
    public Collection<Peso> getPesi(String username) throws ResourceNotFoundException { // NON USATO
        Optional<Utente> opUtente = utenteRepository.findByUsername(username);
        if(opUtente.isEmpty())
            throw new ResourceNotFoundException();

        Utente utente = opUtente.get();
        return utente.getPesi();
    }

    public Peso rimuovi(Long id, String username) throws ResourceNotFoundException{
        // con l'username garantisco che ognuno possa eliminare solo il proprio peso

        Optional<Utente> opUtente = utenteRepository.findByUsername(username);
        if(opUtente.isEmpty())
            throw new ResourceNotFoundException();

        Utente utente = opUtente.get();
        List<Peso> pesiUtente = utente.getPesi();

        for(Peso p:pesiUtente){
            if(p.getId() == id){
                pesoRepository.delete(p);
                return p;
            }
        }

        throw new ResourceNotFoundException();
    }
}
