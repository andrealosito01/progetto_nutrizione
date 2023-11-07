package com.example.pswproject.services;

import com.example.pswproject.entities.Misura;
import com.example.pswproject.entities.Utente;
import com.example.pswproject.repositories.MisuraRepository;
import com.example.pswproject.repositories.UtenteRepository;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.MisuraAlreadyInsertedException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class MisuraService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private MisuraRepository misuraRepository;

    @Transactional(readOnly = true)
    public Collection<Misura> getMisure(String username) throws ResourceNotFoundException{
        Optional<Utente> opUtente = utenteRepository.findByUsername(username);
        if(opUtente.isEmpty())
            throw new ResourceNotFoundException();

        Utente utente = opUtente.get();
        return utente.getMisure();
    }

    public Misura aggiungi(Misura misura, String username) throws MisuraAlreadyInsertedException, ResourceNotFoundException, BadRequestException {
        if(isNotValid(misura))
            throw new BadRequestException();

        Collection<Misura> misure = this.getMisure(username);
        // NON devono essere presenti due misure dello stesso utente nello stesso giorno
        for(Misura m:misure)
            if(misura.getData().equals(m.getData()))
                throw new MisuraAlreadyInsertedException();

        misure.add(misura);
        return misuraRepository.save(misura);
    }

    private boolean isNotValid(Misura misura){
        return misura.getId() != null;
    }

    public Misura modifica(Long id, Misura misura, String username) throws ResourceNotFoundException, BadRequestException, MisuraAlreadyInsertedException {
        if(isNotValid(misura))
            throw new BadRequestException();

        // con l'username garantisco che ognuno possa modificare solo una propria misura
        Collection<Misura> misure = this.getMisure(username);

        for(Misura m:misure)
            if(misura.getData().equals(m.getData()) && !id.equals(m.getId()))
                throw new MisuraAlreadyInsertedException();

        boolean trovato = false;
        for(Misura m:misure){
            if(m.getId().equals(id)){
                misuraRepository.delete(m);
                trovato = true;
            }
        }
        if(trovato) {
            misure.add(misura);
            return misuraRepository.save(misura);
        }else
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
}
