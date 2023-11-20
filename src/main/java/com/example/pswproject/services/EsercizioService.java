package com.example.pswproject.services;

import com.example.pswproject.entities.Esercizio;
import com.example.pswproject.entities.Scheda;
import com.example.pswproject.repositories.EsercizioRepository;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class EsercizioService {

    @Autowired
    private SchedaService schedaService;

    @Autowired
    private EsercizioRepository esercizioRepository;

    @Transactional(readOnly = true)
    public Collection<Esercizio> getEsercizi(String username, Long idScheda) throws ResourceNotFoundException {
        Collection<Scheda> schede = schedaService.getSchede(username);
        for(Scheda scheda:schede)
            if(scheda.getId().equals(idScheda))
                return scheda.getEsercizi();

        throw new ResourceNotFoundException();
    }

    public Esercizio aggiungi(String username, Long idScheda, Esercizio esercizio) throws ResourceNotFoundException, BadRequestException {
        if(isNotValid(esercizio))
            throw new BadRequestException();

        Collection<Esercizio> esercizi = this.getEsercizi(username,idScheda);
        esercizi.add(esercizio);
        return esercizioRepository.save(esercizio);
    }

    private boolean isNotValid(Esercizio esercizio){
        return (esercizio.getId() != null)||(!esercizio.getSerie().isEmpty());
    }

    public Esercizio modifica(String username, Long idScheda, Long id, Esercizio esercizio) throws ResourceNotFoundException{
        Collection<Esercizio> esercizi = this.getEsercizi(username,idScheda);
        for(Esercizio e:esercizi)
            if(e.getId().equals(id)){
                e.setNome(esercizio.getNome());
                e.setDescrizione(esercizio.getDescrizione());
                return e;
            }

        throw new ResourceNotFoundException();
    }

    public Esercizio rimuovi(String username, Long idScheda, Long id) throws ResourceNotFoundException {
        Collection<Esercizio> esercizi = this.getEsercizi(username,idScheda);
        for(Esercizio esercizio:esercizi)
            if(esercizio.getId().equals(id)){
                esercizioRepository.delete(esercizio);
                return esercizio;
            }

        throw new ResourceNotFoundException();
    }

}
