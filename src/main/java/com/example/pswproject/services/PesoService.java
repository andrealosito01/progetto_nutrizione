package com.example.pswproject.services;

import com.example.pswproject.entities.Peso;
import com.example.pswproject.entities.Utente;
import com.example.pswproject.repositories.PesoRepository;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import com.example.pswproject.support.exceptions.WeightAlreadyInsertedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class PesoService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PesoRepository pesoRepository;

    public Peso aggiungi(Peso peso, String username) throws WeightAlreadyInsertedException, ResourceNotFoundException, BadRequestException {
        if(isNotValid(peso))
            throw new BadRequestException();

        Utente utente = utenteService.getUtente(username);
        // NON devono essere presenti due pesi dello stesso utente nello stesso giorno
        if(pesoRepository.existsByDataAndUtente(peso.getData(),utente))
            throw new WeightAlreadyInsertedException();

        peso.setUtente(utente);
        return pesoRepository.save(peso);
    }

    private boolean isNotValid(Peso peso){
        return peso.getId() != null;
    }

    @Transactional(readOnly = true)
    public Collection<Peso> getPesi(String username) throws ResourceNotFoundException {
        Utente utente = utenteService.getUtente(username);
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

    public Page<Peso> getPaginePesiOrdinatiPerData(String username, Integer page, Integer size) throws ResourceNotFoundException {
        Pageable pageable = PageRequest.of(page, size, Sort.by("data").descending());
        Utente utente = utenteService.getUtente(username);
        return pesoRepository.findAllByUtente(utente, pageable);
    }

}
