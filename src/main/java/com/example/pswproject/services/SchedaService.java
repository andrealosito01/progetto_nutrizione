package com.example.pswproject.services;

import com.example.pswproject.entities.Scheda;
import com.example.pswproject.entities.Utente;
import com.example.pswproject.repositories.SchedaRepository;
import com.example.pswproject.repositories.UtenteRepository;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class SchedaService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private SchedaRepository schedaRepository;

    @Transactional(readOnly = true)
    public Collection<Scheda> getSchede(String username) throws ResourceNotFoundException{
        Utente utente = utenteService.getUtente(username);
        return utente.getSchede();
    }

    public Scheda aggiungi(String username, Scheda scheda) throws ResourceNotFoundException, BadRequestException{
        if(isNotValid(scheda))
            throw new BadRequestException();

        Collection<Scheda> schede = this.getSchede(username);
        schede.add(scheda);
        return schedaRepository.save(scheda);
    }

    private boolean isNotValid(Scheda scheda){
        return (scheda.getId() != null)||(!scheda.getEsercizi().isEmpty());
    }

    public Scheda modifica(String username, Long id, Scheda scheda) throws ResourceNotFoundException, BadRequestException{
        if(isNotValid(scheda))
            throw new BadRequestException();

        Collection<Scheda> schede = this.getSchede(username);
        for(Scheda s:schede)
            if(s.getId().equals(id)){
                s.setAttiva(scheda.isAttiva());
                s.setNome(scheda.getNome());
                return s;
            }

        throw new ResourceNotFoundException();
    }

    public Scheda rimuovi(String username, Long id) throws ResourceNotFoundException{
        Collection<Scheda> schede = this.getSchede(username);
        for(Scheda s:schede)
            if(s.getId().equals(id)){
                schedaRepository.delete(s);
                return s;
            }

        throw new ResourceNotFoundException();
    }

}
