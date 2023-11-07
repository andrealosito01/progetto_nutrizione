package com.example.pswproject.services;

import com.example.pswproject.entities.Peso;
import com.example.pswproject.entities.Utente;
import com.example.pswproject.repositories.UtenteRepository;
import com.example.pswproject.repositories.PesoRepository;
import com.example.pswproject.support.exceptions.BadRequestException;
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

    public Peso aggiungi(Peso peso, String username) throws WeightAlreadyInsertedException, ResourceNotFoundException, BadRequestException {
        if(isNotValid(peso))
            throw new BadRequestException();

        Collection<Peso> pesi = this.getPesi(username);
        // NON devono essere presenti due pesi dello stesso utente nello stesso giorno
        for(Peso p:pesi){
            if(peso.getData().equals(p.getData()))
                throw new WeightAlreadyInsertedException();
        }

        pesi.add(peso);
        return pesoRepository.save(peso);
    }

    private boolean isNotValid(Peso peso){
        return peso.getId() != null;
    }

    @Transactional(readOnly = true)
    public Collection<Peso> getPesi(String username) throws ResourceNotFoundException {
        Optional<Utente> opUtente = utenteRepository.findByUsername(username);
        if(opUtente.isEmpty())
            throw new ResourceNotFoundException();

        Utente utente = opUtente.get();
        return utente.getPesi();
    }

    public Peso rimuovi(Long id, String username) throws ResourceNotFoundException{
        // con l'username garantisco che ognuno possa eliminare solo il proprio peso
        Collection<Peso> pesi = this.getPesi(username);

        for(Peso p:pesi){
            if(p.getId().equals(id)){
                pesoRepository.delete(p);
                return p;
            }
        }

        throw new ResourceNotFoundException();
    }
}
