package com.example.pswproject.services;

import com.example.pswproject.entities.Passi;
import com.example.pswproject.entities.Utente;
import com.example.pswproject.repositories.PassiRepository;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.PassiAlreadyInsertedException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
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
public class PassiService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PassiRepository passiRepository;

    @Transactional(readOnly = true)
    public Collection<Passi> getPassi(String username) throws ResourceNotFoundException{
        Utente utente = utenteService.getUtente(username);
        return utente.getPassi();
    }

    public Passi aggiungi(String username, Passi passi) throws ResourceNotFoundException, BadRequestException, PassiAlreadyInsertedException {
        if(isNotValid(passi))
            throw new BadRequestException();

        Utente utente = utenteService.getUtente(username);
        if(passiRepository.existsByDataAndUtente(passi.getData(),utente))
                throw new PassiAlreadyInsertedException();

        passi.setUtente(utente);
        return passiRepository.save(passi);
    }

    private boolean isNotValid(Passi passi){
        return passi.getId() != null;
    }

    public Passi rimuovi(String username, Long id) throws ResourceNotFoundException{
        Collection<Passi> listaPassi = this.getPassi(username);
        for(Passi p:listaPassi)
            if(p.getId().equals(id)){
                passiRepository.delete(p);
                return p;
            }

        throw new ResourceNotFoundException();
    }

    public Page<Passi> getPaginePassiOrdinatiPerData(String username, Integer page, Integer size) throws ResourceNotFoundException {
        Pageable pageable = PageRequest.of(page, size, Sort.by("data").descending());
        Utente utente = utenteService.getUtente(username);
        return passiRepository.findAllByUtente(utente, pageable);
    }

}
