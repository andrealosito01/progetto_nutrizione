package com.example.pswproject.services;

import com.example.pswproject.entities.Misura;
import com.example.pswproject.entities.Utente;
import com.example.pswproject.repositories.MisuraRepository;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.MisuraAlreadyInsertedException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class MisuraService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private MisuraRepository misuraRepository;

    @Transactional(readOnly = true)
    public Collection<Misura> getMisure(String username) throws ResourceNotFoundException{
        Utente utente = utenteService.getUtente(username);
        return utente.getMisure();
    }

    public Misura aggiungi(Misura misura, String username) throws MisuraAlreadyInsertedException, ResourceNotFoundException, BadRequestException {
        if(isNotValid(misura))
            throw new BadRequestException();

        Utente utente = utenteService.getUtente(username);
        // NON devono essere presenti due misure dello stesso utente nello stesso giorno
        if(misuraRepository.existsByDataAndUtente(misura.getData(),utente))
            throw new MisuraAlreadyInsertedException();

        misura.setUtente(utente);
        return misuraRepository.save(misura);
    }

    private boolean isNotValid(Misura misura){
        return misura.getId() != null;
    }

    public Misura modifica(Long id, Misura misura, String username) throws ResourceNotFoundException, BadRequestException, MisuraAlreadyInsertedException {
        if(isNotValid(misura))
            throw new BadRequestException();

        // con l'username garantisco che ognuno possa modificare solo una propria misura
        Utente utente = utenteService.getUtente(username);
        Optional<Misura> misuraStessaDataDiversoId = misuraRepository.findByIdNotAndUtenteAndData(id,utente,misura.getData());
        if(misuraStessaDataDiversoId.isPresent())
            throw new MisuraAlreadyInsertedException();

        Optional<Misura> misuraDaRimuovere = misuraRepository.findByIdAndUtente(id,utente);
        if(misuraDaRimuovere.isPresent()){
            misuraRepository.delete(misuraDaRimuovere.get());
            misura.setUtente(utente);
            return misuraRepository.save(misura);
        }

        throw new ResourceNotFoundException();
    }

    public Misura rimuovi(Long id, String username) throws ResourceNotFoundException{
        // con l'username garantisco che ognuno possa eliminare solo una propria misura
        Collection<Misura> misure = this.getMisure(username);

        for(Misura m:misure){
            if(m.getId().equals(id)){
                misuraRepository.delete(m);
                return m;
            }
        }

        throw new ResourceNotFoundException();
    }

    public Page<Misura> getPagineMisureOrdinatePerData(String username, Integer page, Integer size) throws ResourceNotFoundException {
        Pageable pageable = PageRequest.of(page, size, Sort.by("data").descending());
        Utente utente = utenteService.getUtente(username);
        return misuraRepository.findAllByUtente(utente, pageable);
    }
}
