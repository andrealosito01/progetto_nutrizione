package com.example.pswproject.services;

import com.example.pswproject.entities.Misura;
import com.example.pswproject.entities.Utente;
import com.example.pswproject.repositories.MisuraRepository;
import com.example.pswproject.repositories.UtenteRepository;
import com.example.pswproject.support.exceptions.MisuraAlreadyInsertedException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MisuraService {

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    MisuraRepository misuraRepository;

    @Transactional(readOnly = true)
    public Collection<Misura> getMisure(String username) throws ResourceNotFoundException{
        Optional<Utente> opUtente = utenteRepository.findByUsername(username);
        if(opUtente.isEmpty())
            throw new ResourceNotFoundException();

        Utente utente = opUtente.get();
        return utente.getMisure();
    }

    public Misura aggiungi(Misura misura, String username) throws MisuraAlreadyInsertedException, ResourceNotFoundException{
        Optional<Utente> opUtente = utenteRepository.findByUsername(username);
        if(opUtente.isEmpty())
            throw new ResourceNotFoundException();

        Utente utente = opUtente.get();
        // NON devono essere presenti due misure dello stesso utente nello stesso giorno
        for(Misura m:utente.getMisure())
            if(misura.getData().equals(m.getData()))
                throw new MisuraAlreadyInsertedException();

        utente.getMisure().add(misura);
        return misuraRepository.save(misura);
    }

    public Misura modifica(Long id, Misura misura, String username) throws ResourceNotFoundException{
        // con l'username garantisco che ognuno possa modificare solo una propria misura

        Optional<Utente> opUtente = utenteRepository.findByUsername(username);
        if(opUtente.isEmpty())
            throw new ResourceNotFoundException();

        Utente utente = opUtente.get();
        List<Misura> listaMisure = utente.getMisure();

        for(Misura m:listaMisure){
            if(m.getId() == id){
                m.setBraccioDx(misura.getBraccioDx());
                m.setBraccioSx(misura.getBraccioSx());
                m.setTorace(misura.getTorace());
                m.setVita(misura.getVita());
                m.setFianchi(misura.getFianchi());
                m.setGambaDx(misura.getGambaDx());
                m.setGambaSx(misura.getGambaSx());
                return m;
            }
        }

        throw new ResourceNotFoundException();
    }

    public Misura rimuovi(Long id, String username) throws ResourceNotFoundException{
        // con l'username garantisco che ognuno possa eliminare solo una propria misura

        Optional<Utente> opUtente = utenteRepository.findByUsername(username);
        if(opUtente.isEmpty())
            throw new ResourceNotFoundException();

        Utente utente = opUtente.get();
        List<Misura> listaMisure = utente.getMisure();

        for(Misura m:listaMisure){
            if(m.getId() == id){
                misuraRepository.delete(m);
                return m;
            }
        }

        throw new ResourceNotFoundException();
    }
}
