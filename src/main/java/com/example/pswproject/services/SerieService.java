package com.example.pswproject.services;

import com.example.pswproject.entities.Esercizio;
import com.example.pswproject.entities.Serie;
import com.example.pswproject.repositories.SerieRepository;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import com.example.pswproject.support.exceptions.SerieAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class SerieService {

    @Autowired
    private EsercizioService esercizioService;

    @Autowired
    private SerieRepository serieRepository;

    @Transactional(readOnly = true)
    public Collection<Serie> getSerie(String username, Long idScheda, Long idEsercizio) throws ResourceNotFoundException {
        Collection<Esercizio> esercizi = esercizioService.getEsercizi(username,idScheda);
        for(Esercizio e:esercizi)
            if(e.getId().equals(idEsercizio))
                return e.getSerie();

        throw new ResourceNotFoundException();
    }

    public Serie aggiungi(String username, Long idScheda, Long idEsercizio, Serie serie) throws ResourceNotFoundException, BadRequestException, SerieAlreadyExistsException{
        if(isNotValid(serie))
            throw new BadRequestException();

        Collection<Serie> listaSerie = this.getSerie(username, idScheda, idEsercizio);
        for(Serie s:listaSerie)
            if(s.getNumero().equals(serie.getNumero()))
                throw new SerieAlreadyExistsException();

        listaSerie.add(serie);
        return serieRepository.save(serie);
    }

    private boolean isNotValid(Serie serie){
        return serie.getId() != null;
    }

    public Serie modifica(String username, Long idScheda, Long idEsercizio, Long id, Serie serie) throws ResourceNotFoundException,BadRequestException,SerieAlreadyExistsException{
        if(isNotValid(serie))
            throw new BadRequestException();

        Collection<Serie> listaSerie = this.getSerie(username, idScheda, idEsercizio);

        for(Serie s:listaSerie)
            if((s.getNumero().equals(serie.getNumero()))&&(!s.getId().equals(id)))
                throw new SerieAlreadyExistsException();

        boolean trovato = false;
        for(Serie s:listaSerie)
            if(s.getId().equals(id)){
                serieRepository.delete(s);
                trovato = true;
            }

        if(trovato) {
            listaSerie.add(serie);
            return serieRepository.save(serie);
        }else
            throw new ResourceNotFoundException();
    }

    public Serie rimuovi(String username, Long idScheda, Long idEsercizio, Long id) throws ResourceNotFoundException{
        Collection<Serie> listaSerie = this.getSerie(username, idScheda, idEsercizio);
        for(Serie serie:listaSerie)
            if(serie.getId().equals(id)){
                serieRepository.delete(serie);
                return serie;
            }

        throw new ResourceNotFoundException();
    }

}
